package com.dell.poweredge.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TelemetryData {
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
