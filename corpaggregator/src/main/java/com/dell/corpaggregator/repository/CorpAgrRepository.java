package com.dell.corpaggregator.repository;

import com.dell.corpaggregator.model.TelemetryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorpAgrRepository extends JpaRepository<TelemetryData, Long> {

    @Query("SELECT t FROM TelemetryData t WHERE t.node = :nodeName")
    List<TelemetryData> findByNode(@Param("nodeName") String nodeName);
}
