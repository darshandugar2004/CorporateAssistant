// src/main/java/com/example/llmpoc/controller/TestController.java
package com.example.llmpoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    
    @GetMapping("/test")
    public String testEndpoint() {
        return "API is working! Current time: " + java.time.LocalDateTime.now();
    }
}