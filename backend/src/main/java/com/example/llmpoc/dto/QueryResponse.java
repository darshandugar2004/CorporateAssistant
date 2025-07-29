package com.example.llmpoc.dto;  // Or com.llmpoc.dto

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class QueryResponse {
    private String userId;
    private LocalDateTime timestamp;
    private int predictedClass;
    private double confidence;
    private String processedQuery;
}