package main.java.com.example.converse.controller;

import main.java.com.example.converse.model.MessageRequest;
import main.java.com.example.converse.model.MessageResponse;
import main.java.com.example.converse.service.ConverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/converse")
@CrossOrigin(origins = "*") // 必要に応じてCORS設定
public class ConverseController {

    private final ConverseService converseService;

    @Autowired
    public ConverseController(ConverseService converseService) {
        this.converseService = converseService;
    }

    @PostMapping
    public MessageResponse converse(@RequestBody MessageRequest request) {
        // 日記内容を受け取り、AIコメントを生成
        return converseService.generateComment(request);
    }
}