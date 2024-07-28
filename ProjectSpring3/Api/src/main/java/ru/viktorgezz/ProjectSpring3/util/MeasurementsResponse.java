package ru.viktorgezz.ProjectSpring3.util;

import ru.viktorgezz.ProjectSpring3.dto.MeasurementDTO;
import ru.viktorgezz.ProjectSpring3.models.Measurement;

import java.util.List;

public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementsResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
