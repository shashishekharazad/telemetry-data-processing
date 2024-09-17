package com.dell.corpaggregator.service;

import com.dell.corpaggregator.model.TelemetryData;
import com.dell.corpaggregator.repository.CorpAgrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorpAggregatorService {

    @Autowired
    private CorpAgrRepository repository;

    public List<TelemetryData> getTelemetryData() {
        return repository.findAll();
    }
    public List<TelemetryData> getTelemetryByNode(String nodeName) {
        return repository.findByNode(nodeName);
    }

    public void saveTelemetryData(TelemetryData data) {
        repository.save(data);
    }
}
