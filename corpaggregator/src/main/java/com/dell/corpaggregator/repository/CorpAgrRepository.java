package com.dell.corpaggregator.repository;

import com.dell.corpaggregator.model.TelemetryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorpAgrRepository extends JpaRepository<TelemetryData, Long> {

}
