package ru.viktorgezz.ProjectSpring3.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class MeasurementDTO {

    @Range(min = -100, max = 100, message = "Temperature is more than 100 or less than -100.")
    private Double value;

    @NotNull(message = "Raining is not empty")
    private Boolean raining;

    @NotNull(message = "Sensor name is not empty")
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
