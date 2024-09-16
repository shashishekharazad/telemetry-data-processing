package com.dell.corpaggregator.controller;

import com.dell.corpaggregator.model.ResponseDTO;
import com.dell.corpaggregator.model.TelemetryData;
import com.dell.corpaggregator.service.CorpAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/corpag")
public class CorpAggregatorController {

    @Autowired
    private CorpAggregatorService service;

    @PostMapping("/telemetry")
    public ResponseDTO receiveData(@RequestBody TelemetryData data) {
        if (data != null) {
            service.saveTelemetryData(data);
        }
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Received data successfully!");
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    @GetMapping("/showtelemetry")
    public List<TelemetryData> getTelemetryData() {
        return service.getTelemetryByNode();
    }
}
