package com.dell.corpaggregator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "telemetries")
public class TelemetryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String node;
    private double temperature;
    private double networkSpeed;
    private double diskUtilization;
    private double cpuUtilization;
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "TelemetryData{" +
                "node=" + node +
                ", temperature=" + temperature +
                ", networkSpeed=" + networkSpeed +
                ", diskUtilization=" + diskUtilization +
                ", cpuUtilization=" + cpuUtilization +
                ", timestamp=" + timestamp +
                '}';
    }
}
