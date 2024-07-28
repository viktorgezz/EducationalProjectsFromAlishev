package ru.viktorgezz.ProjectSpring3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.viktorgezz.ProjectSpring3.models.Sensor;
import ru.viktorgezz.ProjectSpring3.services.SensorService;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.getSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "There is already a sensor with the same name!");
        }
    }
}
