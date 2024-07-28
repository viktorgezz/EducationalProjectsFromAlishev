package ru.viktorgezz.ProjectSpring3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.viktorgezz.ProjectSpring3.dao.SensorDao;
import ru.viktorgezz.ProjectSpring3.dto.MeasurementDTO;
import ru.viktorgezz.ProjectSpring3.models.Measurement;

import java.util.List;

@Component
public class MeasurementValidator implements Validator {

    private final SensorDao sensorDao;

    @Autowired
    public MeasurementValidator(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        List<String> names = sensorDao.getAllName();

        if (names.contains(measurementDTO.getSensor().getName())) {
            errors.rejectValue("name", "", "There is no such name");
        }
    }
}
