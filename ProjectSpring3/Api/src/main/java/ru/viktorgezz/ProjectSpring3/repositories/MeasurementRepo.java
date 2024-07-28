package ru.viktorgezz.ProjectSpring3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.ProjectSpring3.models.Measurement;

@Repository
public interface MeasurementRepo extends JpaRepository<Measurement,Integer> {
}
