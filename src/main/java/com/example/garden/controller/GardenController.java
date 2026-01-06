package com.example.garden.controller;

import com.example.garden.model.Measurement;
import com.example.garden.service.MeasurementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GardenController {

    private final MeasurementService measurementService;

    public GardenController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public Measurement addMeasurement(
            @RequestParam Long sensorId,
            @RequestParam double temperature,
            @RequestParam double humidity
    ) {
        return measurementService.addMeasurement(sensorId, temperature, humidity);
    }

    @GetMapping("/history/{sensorId}")
    public List<Measurement> history(@PathVariable Long sensorId) {
        return measurementService.getHistory(sensorId);
    }

    @GetMapping("/latest/{sensorId}")
    public Measurement latest(@PathVariable Long sensorId) {
        return measurementService.getHistory(sensorId)
                .stream()
                .findFirst()
                .orElse(null);
    }
}