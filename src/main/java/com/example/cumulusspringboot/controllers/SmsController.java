package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.config.TwilioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@RestController
public class SmsController {
    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsController(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @PostMapping("/api/sms")
    public ResponseEntity<?> sendSms() {
        Twilio.init(twilioConfig.getAccountsid(), twilioConfig.getAuthtoken());
        Message.creator(new PhoneNumber("+21695188943"), new PhoneNumber(twilioConfig.getNumber()), "message").create();
        return ResponseEntity.ok("SMS sent successfully.");
    }
}

