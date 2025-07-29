package com.example.llmpoc.dto;  // Or com.llmpoc.dto if using that package

import lombok.Data;

@Data
public class QueryRequest {
    private String userId;
    private String query;
}