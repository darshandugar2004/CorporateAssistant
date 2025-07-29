package com.example.llmpoc.controller;

import com.example.llmpoc.dto.ApiResponse;
import com.example.llmpoc.dto.QueryRequest;
import com.example.llmpoc.dto.QueryResponse;
import com.example.llmpoc.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/llm")
public class LLMController {

    @Autowired
    private LLMService llmService;

    @PostMapping("/query")
    public ResponseEntity<ApiResponse<QueryResponse>> queryModel(@RequestBody QueryRequest request) {
        try {
            QueryResponse response = llmService.processQuery(request.getUserId(), request.getQuery());
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}