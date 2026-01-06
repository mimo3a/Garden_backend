package com.example.garden.service;

import com.example.garden.model.Measurement;
import com.example.garden.model.Sensor;
import com.example.garden.repository.MeasurementRepository;
import com.example.garden.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    public MeasurementService(MeasurementRepository measurementRepository,
                              SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public Measurement addMeasurement(Long sensorId, double temperature, double humidity, LocalDateTime timestamp) {

        sensorRepository.findById(sensorId).orElseThrow();

        Measurement m = new Measurement();
        m.setSensorId(sensorId);
        m.setTemperature(temperature);
        m.setHumidity(humidity);
        m.setTimestamp(timestamp);

        return measurementRepository.save(m);
    }

    public List<Measurement> getHistory(Long sensorId) {
        return measurementRepository.findBySensorIdOrderByTimestampDesc(sensorId);
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public Measurement getLatest(Long sensorId) {
        return measurementRepository.findBySensorIdOrderByTimestampDesc(sensorId)
                .stream()
                .findFirst()
                .orElse(null);
    }
}