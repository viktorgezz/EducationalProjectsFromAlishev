package ru.viktorgezz.ProjectSpring3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.ProjectSpring3.models.Sensor;
import ru.viktorgezz.ProjectSpring3.repositories.SensorRepo;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepo sensorRepo;

    @Autowired
    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepo.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepo.save(sensor);
    }

}
