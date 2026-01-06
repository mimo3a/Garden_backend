package com.example.garden.controller;

import com.example.garden.model.Measurement;
import com.example.garden.service.MeasurementService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService ms) {
        this.measurementService = ms;
    }

    // JSON input
    @PostMapping
    public Measurement addMeasurement(@RequestBody MeasurementDTO dto) {
        return measurementService.addMeasurement(
                dto.sensorId(),
                dto.temperature(),
                dto.humidity(),
                dto.timestamp()
        );
    }

    @GetMapping("/history/{sensorId}")
    public List<Measurement> getHistory(@PathVariable Long sensorId) {
        return measurementService.getHistory(sensorId);
    }

    @GetMapping("/latest/{sensorId}")
    public Measurement getLatest(@PathVariable Long sensorId) {
        List<Measurement> h = measurementService.getHistory(sensorId);
        return h.isEmpty() ? null : h.get(0);
    }

    public record MeasurementDTO(Long sensorId, double temperature, double humidity, LocalDateTime timestamp) {}
}
