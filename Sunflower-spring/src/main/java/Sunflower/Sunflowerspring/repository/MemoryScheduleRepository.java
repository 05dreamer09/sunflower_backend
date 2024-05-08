package Sunflower.Sunflowerspring.repository;

import Sunflower.Sunflowerspring.domain.Schedule;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryScheduleRepository implements ScheduleRepository {

    private static Map<Long, Schedule> store = new HashMap<>(); // 저장 공간
    private static Long sequence = 0L; // id를 생성하기 위한 sequence


    @Override
    public Schedule save(Schedule schedule) {
        schedule.setsId(++sequence);
        store.put(schedule.getsId(), schedule);
        return schedule;
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Schedule> findByName(String name) {
        return store.values().stream()
                .filter(schedule -> schedule.getsName().equals(name))
                .findAny();
    }

    @Override
    public List<Schedule> findAll() {
        return store.values().stream().toList();
    }
}
