package com.example.cumulusspringboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "twilio")
@Getter
@Setter
public class TwilioConfig {
    private String accountsid;
    private String authtoken;
    private String number;

    // getters and setters
}

