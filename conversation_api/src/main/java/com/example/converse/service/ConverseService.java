package com.example.converse.service;

import com.example.converse.model.MessageRequest;
import com.example.converse.model.MessageResponse;
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
        String template = "{\n"
         + "  \"input\": {\n"
         + "    \"messages\": [\n"
         + "      {\n"
         + "        \"role\": \"user\",\n"
         + "        \"content\": \"以下はユーザーが書いた日記です。内容に対して優しく、前向きなコメントを返してください。\\n\\n日記:\\n%s\"\n"
         + "      }\n"
         + "    ]\n"
         + "  },\n"
         + "  \"max_tokens\": 300\n"
         + "}";
        return String.format(template, diaryText);
    }

    /**
     * Bedrockのレスポンスからコメント部分を抽出する（簡易実装）
     */
    private String extractComment(String responseBody) {
        // Claudeなどのモデルが返すJSON構造に応じて調整が必要
        // ここでは仮に "content" フィールドを抽出する簡易処理
        int start = responseBody.indexOf("\"content\":\"");
        if (start == -1) return "コメントの抽出に失敗しました。";

        start += "\"content\":\"".length();
        int end = responseBody.indexOf("\"", start);
        return responseBody.substring(start, end).replace("\\n", "\n");
    }
}
