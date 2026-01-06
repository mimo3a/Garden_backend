package com.example.garden.controller;

import com.example.garden.model.Measurement;
import com.example.garden.service.MeasurementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService ms) {
        this.measurementService = ms;
    }

    @PostMapping
    public Measurement add(@RequestParam Long sensorId,
                           @RequestParam double temperature,
                           @RequestParam double humidity) {
        return measurementService.addMeasurement(sensorId, temperature, humidity);
    }
}
