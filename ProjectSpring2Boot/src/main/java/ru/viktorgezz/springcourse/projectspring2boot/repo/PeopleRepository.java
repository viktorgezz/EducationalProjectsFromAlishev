package ru.viktorgezz.springcourse.projectspring2boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.springcourse.projectspring2boot.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

}
