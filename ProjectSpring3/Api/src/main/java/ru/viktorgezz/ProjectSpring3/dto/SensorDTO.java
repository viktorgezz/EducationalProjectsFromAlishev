package ru.viktorgezz.ProjectSpring3.dto;

import jakarta.validation.constraints.NotEmpty;

public class SensorDTO {

    @NotEmpty(message = "Name is not Empty")
    private String name;

    public SensorDTO() {
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
