package com.example.llmpoc.service;

import com.example.llmpoc.dto.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class LLMService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EncryptionService encryptionService;

    @Value("${llm.model.url}")
    private String modelUrl;

    @Value("${encryption.key}")
    private String encryptionKey;

    public QueryResponse processQuery(String userId, String query) throws Exception {
        // Create data packet
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String dataPacket = userId + "|" + timestamp + "|" + query;
        
        // Encrypt the data packet
        String encryptedData = encryptionService.encrypt(dataPacket);
        
        // Call the model API
        Map<String, String> request = new HashMap<>();
        request.put("encrypted_data", encryptedData);
        
        ResponseEntity<Map> response = restTemplate.postForEntity(
            modelUrl + "/predict", 
            request, 
            Map.class
        );
        
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to get response from model");
        }
        
        // Decrypt the response
        String encryptedResponse = (String) response.getBody().get("encrypted_response");
        String decryptedResponse = encryptionService.decrypt(encryptedResponse);
        
        // Parse the response
        String[] parts = decryptedResponse.split("\\|");
        if (parts.length != 4) {
            throw new RuntimeException("Invalid response format from model");
        }
        
        QueryResponse queryResponse = new QueryResponse();
        queryResponse.setUserId(parts[0]);
        queryResponse.setTimestamp(LocalDateTime.parse(parts[1], DateTimeFormatter.ISO_DATE_TIME));
        queryResponse.setPredictedClass(Integer.parseInt(parts[2]));
        queryResponse.setConfidence(Double.parseDouble(parts[3]));
        queryResponse.setProcessedQuery(query);
        
        return queryResponse;
    }
}