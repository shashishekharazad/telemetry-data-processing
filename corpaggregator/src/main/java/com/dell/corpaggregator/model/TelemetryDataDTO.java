package com.dell.corpaggregator.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TelemetryDataDTO {

    private String node;
    private double temperature;
    private double networkSpeed;
    private double diskUtilization;
    private double cpuUtilization;
    private LocalDateTime timestamp;


    @Override
    public String toString() {
        return "TelemetryData{" +
                "temperature=" + temperature +
                ", networkSpeed=" + networkSpeed +
                ", diskUtilization=" + diskUtilization +
                ", cpuUtilization=" + cpuUtilization +
                ", timestamp=" + timestamp +
                '}';
    }


}
