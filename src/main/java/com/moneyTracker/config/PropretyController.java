package com.moneyTracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/properties")
public class PropretyController {
    @Value("${environment.name}")
    private String environmentName;

    @GetMapping
    public String getEnvironmentName() {
        return environmentName;
    }
}
