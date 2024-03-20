package Sunflower.Sunflowerspring.repository;

import Sunflower.Sunflowerspring.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Schedule save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    Optional<Schedule> findByName(String name);
    List<Schedule> findAll();


}
