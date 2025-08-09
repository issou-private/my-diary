package com.example.converse.service;

import com.example.converse.model.MessageRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ConverseService {

    private final BedrockRuntimeClient bedrockClient;

    @Value("${bedrock.model.id}")
    private String modelId;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ConverseService(BedrockRuntimeClient bedrockClient) {
        this.bedrockClient = bedrockClient;
    }

    /**
     * ユーザーの日記をもとに、BedrockのConverse APIを使ってAIコメントを生成する
     *
     * @param request MessageRequest（Reactから送られる日記本文）
     * @return AIによるコメント
     */
    public String generateComment(MessageRequest request) {
        String diaryText = request.getContent();
        String payload = buildConversePayload(diaryText);

        InvokeModelRequest invokeRequest = InvokeModelRequest.builder()
                .modelId(modelId)
                .contentType("application/json")
                .accept("application/json")
                .body(SdkBytes.fromString(payload, StandardCharsets.UTF_8))
                .build();

        InvokeModelResponse response = bedrockClient.invokeModel(invokeRequest);
        String responseBody = response.body().asUtf8String();

        return extractComment(responseBody);
    }

    /**
     * Bedrock Converse API用のmessages形式JSONを構築する
     */
    private String buildConversePayload(String diaryText) {
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, String>> messages = new ArrayList<>();

            // system promptはuserメッセージに含める
            Map<String, String> userMsg = new HashMap<>();
            userMsg.put("role", "user");
            userMsg.put("content", "あなたはちょっとおせっかいだけど人情深いオカンです。関西弁でコメントしてください。\n\n日記:\n" + diaryText);

            messages.add(userMsg);

            map.put("messages", messages);
            map.put("max_tokens", 300);
            map.put("anthropic_version", "bedrock-2023-05-31");

            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException("Converse API用JSONの生成に失敗しました", e);
        }
    }

    /**
     * Bedrockのレスポンスからコメント部分を抽出する
     */
    private String extractComment(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            // Claude 3系は "content" フィールドで返す
            if (root.has("content")) {
                return root.get("content").asText();
            } else if (root.has("completion")) {
                return root.get("completion").asText();
            } else {
                return "コメントの抽出に失敗しました。";
            }
        } catch (Exception e) {
            return "コメントの抽出に失敗しました。";
        }
    }
}
