package com.dell.poweredge.controller;

import com.dell.poweredge.service.PoweredgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PoweredgeController {

    private final PoweredgeService service;

    @Autowired
    public PoweredgeController(PoweredgeService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String testApi(){
        return "Service is running!";
    }
    @GetMapping("/send")
    public void sendTelemetry(){
        service.sendTelemetryData();
    }
}
