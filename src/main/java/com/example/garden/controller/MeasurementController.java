package com.example.garden.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;    // <<< ВАЖНО!
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.garden.model.Measurement;
import com.example.garden.service.MeasurementService;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService ms) {
        this.measurementService = ms;
    }

    @PostMapping
    public Measurement add(
            @RequestParam Long sensorId,
            @RequestParam double temperature,
            @RequestParam double humidity) {

        return measurementService.addMeasurement(
                sensorId,
                temperature,
                humidity,
                LocalDateTime.now()
        );
    }

    @GetMapping("/latest/{sensorId}")
    public Measurement latest(@PathVariable Long sensorId) {
        return measurementService.getHistory(sensorId)
                                 .stream()
                                 .findFirst()
                                 .orElse(null);
    }
}
