package Sunflower.Sunflowerspring.service;

import Sunflower.Sunflowerspring.domain.Schedule;
import Sunflower.Sunflowerspring.dto.ScheduleDto;
import Sunflower.Sunflowerspring.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository)   {
        this.scheduleRepository = scheduleRepository;
    }

    public void saveSchedule(ScheduleDto scheduleDto)   {
        Schedule schedule = new Schedule();
        schedule.setsName(scheduleDto.getsName());
        schedule.setuName(scheduleDto.getuName());
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEndTime(scheduleDto.getEndTime());
        schedule.setLocation(scheduleDto.getLocation());
        schedule.setsDetail(scheduleDto.getsDetail());

        scheduleRepository.save(schedule);
    }

    public ScheduleDto findScheduleByName(String uName) {
        Schedule schedule = scheduleRepository.findByName(uName).get();

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setsName(schedule.getsName());
        scheduleDto.setuName(schedule.getuName());
        scheduleDto.setStartTime(schedule.getStartTime());
        scheduleDto.setEndTime(schedule.getEndTime());
        scheduleDto.setLocation(schedule.getLocation());
        scheduleDto.setsDetail(schedule.getsDetail());

        return scheduleDto;
    }
}
