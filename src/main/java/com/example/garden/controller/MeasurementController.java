package com.example.garden.controller;

import com.example.garden.model.Measurement;
import com.example.garden.service.MeasurementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    // Добавить измерение
    @PostMapping
    public Measurement addMeasurement(@RequestBody MeasurementRequest request) {
        return measurementService.addMeasurement(
                request.getSensorId(),
                request.getTemperature(),
                request.getHumidity()
        );
    }

    // Получить все измерения
    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.getAll();
    }

    // История по sensorId
    @GetMapping("/{sensorId}")
    public List<Measurement> getBySensor(@PathVariable Long sensorId) {
        return measurementService.getHistory(sensorId);
    }

    // Последнее измерение
    @GetMapping("/{sensorId}/latest")
    public Measurement getLatest(@PathVariable Long sensorId) {
        return measurementService.getLatest(sensorId);
    }

    // DTO-класс
    public static class MeasurementRequest {
        private Long sensorId;
        private double temperature;
        private double humidity;

        public Long getSensorId() { return sensorId; }
        public double getTemperature() { return temperature; }
        public double getHumidity() { return humidity; }
    }
}
