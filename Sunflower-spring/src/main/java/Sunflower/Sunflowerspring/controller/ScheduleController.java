package Sunflower.Sunflowerspring.controller;


import Sunflower.Sunflowerspring.domain.Schedule;
import Sunflower.Sunflowerspring.dto.ScheduleDto;
import Sunflower.Sunflowerspring.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService)  {
        this.scheduleService = scheduleService;
    }

    @PostMapping("Schedule")
    public void save(@RequestBody ScheduleDto scheduleDto)  {
        scheduleService.saveSchedule(scheduleDto);
    }

    @GetMapping("Schedule/{sName}")
    public ScheduleDto findScheduleBysName(@PathVariable String sName)  {
        return scheduleService.findScheduleByName(sName);
    }


}
