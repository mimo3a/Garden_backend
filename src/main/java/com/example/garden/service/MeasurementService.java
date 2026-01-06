package com.example.garden.service;

import com.example.garden.model.Measurement;
import com.example.garden.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository repo;

    public MeasurementService(MeasurementRepository repo) {
        this.repo = repo;
    }

    public Measurement addMeasurement(Long sensorId, double temperature, double humidity) {
        Measurement m = new Measurement();
        m.setSensorId(sensorId);
        m.setTemperature(temperature);
        m.setHumidity(humidity);
        m.setTimestamp(LocalDateTime.now());
        return repo.save(m);
    }

    public List<Measurement> getHistory(Long sensorId) {
        return repo.findBySensorIdOrderByTimestampDesc(sensorId);
    }
}