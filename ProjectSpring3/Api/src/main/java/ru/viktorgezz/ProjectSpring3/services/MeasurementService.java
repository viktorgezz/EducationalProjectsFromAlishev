package ru.viktorgezz.ProjectSpring3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.ProjectSpring3.models.Measurement;
import ru.viktorgezz.ProjectSpring3.repositories.MeasurementRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepo measurementRepo;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepo measurementRepo, SensorService sensorService) {
        this.measurementRepo = measurementRepo;
        this.sensorService = sensorService;
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepo.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepo.findAll();
    }

    public Long findRainyDaysCount() {
        return findAll().stream()
                .filter(Measurement::isRaining)
                .count();
    }

    public List<Double> getTemperatures() {
        return findAll().stream().map(Measurement::getValue).collect(Collectors.toList());
    }

    public List<Long> getIds() {
        return findAll().stream().map(Measurement::getId).collect(Collectors.toList());
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorService.getSensorByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
    }

}
