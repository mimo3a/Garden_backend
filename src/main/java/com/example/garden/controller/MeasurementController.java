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

// REST controller that exposes endpoints to create and read Measurements.
// Base path: /api/measurements
@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    // Constructor-based dependency injection for the MeasurementService.
    // Keeping the original parameter name 'ms' to match existing usages.
    public MeasurementController(MeasurementService ms) {
        this.measurementService = ms;
    }

    // POST endpoint to add a new measurement.
    // Expects request parameters: sensorId (Long), temperature (double), humidity (double).
    // The timestamp is set to now() inside the controller.
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

    // GET endpoint that returns the latest measurement for a given sensor.
    // Path: /api/measurements/latest/{sensorId}
    // If no measurements exist for the sensor, returns null.
    @GetMapping("/latest/{sensorId}")
    public Measurement latest(@PathVariable Long sensorId) {
        return measurementService.getHistory(sensorId)
                                 .stream()
                                 .findFirst()
                                 .orElse(null);
    }
}