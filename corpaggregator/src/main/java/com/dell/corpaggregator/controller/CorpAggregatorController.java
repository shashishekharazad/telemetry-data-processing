package com.dell.corpaggregator.controller;

import com.dell.corpaggregator.model.TelemetryDataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corpag")
public class CorpAggregatorController {

    @PostMapping("/telemetry")
    public String receiveData(@RequestBody TelemetryDataDTO data){
        System.out.println(data);
        System.out.println("Received data successfully at " + data.getTimestamp());

        return "Received data successfully!";
    }
}
