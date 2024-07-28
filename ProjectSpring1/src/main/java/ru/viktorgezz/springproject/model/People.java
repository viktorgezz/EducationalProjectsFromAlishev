package ru.viktorgezz.springproject.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class People {
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int dateBirth;

    public People(int id, String age, int dateBirth) {
        this.id = id;
        this.name = age;
        this.dateBirth = dateBirth;
    }

    // Конструктор по умолчанию нужен для Spring
    public People() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }
}
