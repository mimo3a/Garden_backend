package com.example.garden.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;    // <<< ВАЖНО!
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.garden.dto.MeasurementRequest;
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
    public Measurement add(@RequestBody MeasurementRequest req) {
        return measurementService.addMeasurement(
            req.getSensorId(),
            req.getTemperature(),
            req.getHumidity(),
            req.getTimestamp() != null
                ? req.getTimestamp()
                : LocalDateTime.now()
        );
    }

    @GetMapping
    public List<Measurement> all() {
        return measurementService.getAll();
    }


    // GET endpoint that returns the latest measurement for a given sensor.
    // Path: /api/measurements/latest/{sensorId}
    // If no measurements exist for the sensor, returns 404 Not Found instead of an empty body.
    @GetMapping("/latest/{sensorId}")
    public ResponseEntity<Measurement> latest(@PathVariable Long sensorId) {
        Optional<Measurement> opt = measurementService.getHistory(sensorId)
                                                      .stream()
                                                      .findFirst();
        return opt.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}