package ru.viktorgezz.ProjectSpring3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.ProjectSpring3.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepo extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findByName(String name);
}
