package com.drip.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.Map;

@Service
public class EvaluationService {

    @Value("classpath:evaluation.json")
    private Resource evaluationFile;

    private Map<String, String> evaluationMap;

    @PostConstruct
    public void init() throws IOException {
        // 使用 ObjectMapper 读取 JSON 文件并将其转换为 Map
        ObjectMapper objectMapper = new ObjectMapper();
        this.evaluationMap = objectMapper.readValue(evaluationFile.getInputStream(), Map.class);
    }

    public String getEvaluation(int correctCount) {
        String level;
        if (correctCount >= 0 && correctCount <= 6) {
            level = "level5";
        } else if (correctCount >= 7 && correctCount <= 12) {
            level = "level4";
        } else if (correctCount >= 13 && correctCount <= 18) {
            level = "level3";
        } else if (correctCount >= 19 && correctCount <= 24) {
            level = "level2";
        } else {
            level = "level1";
        }

        return evaluationMap.get(level);
    }
}
