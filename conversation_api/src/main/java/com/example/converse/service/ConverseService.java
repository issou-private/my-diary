package com.example.converse.service;

import com.example.converse.model.MessageRequest;
import com.example.converse.model.MessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import java.nio.charset.StandardCharsets;

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
        String promptPayload = buildPrompt(diaryText);

        InvokeModelRequest invokeRequest = InvokeModelRequest.builder()
                .modelId(modelId)
                .contentType("application/json")
                .accept("application/json")
                .body(SdkBytes.fromString(promptPayload, StandardCharsets.UTF_8))
                .build();

        InvokeModelResponse response = bedrockClient.invokeModel(invokeRequest);
        String responseBody = response.body().asUtf8String();

        return extractComment(responseBody);
    }

    /**
     * Bedrock Converse API用のプロンプトJSONを構築する
     */
    private String buildPrompt(String diaryText) {
        try {
            java.util.HashMap<String, Object> map = new java.util.HashMap<>();
            String prompt = String.format(
                "Human: 以下はユーザーが書いた日記です。あなたはちょっとおせっかいだけど人情深いオカンとして、関西弁でコメントしてください。\n\n日記:\n%s\n\nAssistant:",
                diaryText
            );
            map.put("prompt", prompt);
            map.put("max_tokens_to_sample", 300);
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException("プロンプトJSONの生成に失敗しました", e);
        }
    }

    /**
     * Bedrockのレスポンスからコメント部分を抽出する（簡易実装）
     */
    private String extractComment(String responseBody) {
        try {
            com.fasterxml.jackson.databind.JsonNode root = objectMapper.readTree(responseBody);
            if (root.has("completion")) {
                return root.get("completion").asText();
            } else if (root.has("content")) {
                return root.get("content").asText();
            } else {
                return "コメントの抽出に失敗しました。";
            }
        } catch (Exception e) {
            return "コメントの抽出に失敗しました。";
        }
    }
}
