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

    // Добавить измерение
    public Measurement addMeasurement(Long sensorId, double temperature, double humidity) {
        Measurement m = new Measurement();
        m.setSensorId(sensorId);
        m.setTemperature(temperature);
        m.setHumidity(humidity);
        m.setTimestamp(LocalDateTime.now());
        return repo.save(m);
    }

    // История по сенсору (новые → старые)
    public List<Measurement> getHistory(Long sensorId) {
        return repo.findBySensorIdOrderByTimestampDesc(sensorId);
    }

    // Вернуть все измерения
    public List<Measurement> getAll() {
        return repo.findAll();   // <-- испольуем repo, НЕ measurementRepository
    }

    // Последнее измерение по сенсору
    public Measurement getLatest(Long sensorId) {
        return repo.findBySensorIdOrderByTimestampDesc(sensorId)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
