package com.example.garden.repository;

import com.example.garden.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findBySensorIdOrderByTimestampDesc(Long sensorId);
}
