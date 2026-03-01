package com.example.garden.repository;

import com.example.garden.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
