package Sunflower.Sunflowerspring.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.web.bind.annotation.GetMapping;

public class ScheduleDto {
    private Long sId;
    private String uName;
    private String sName;
    private String startTime;//(UTC*, RFC5545의 DATE-TIME형식(예: 2022-05-17T112:00:00Z)

    private String endTime; //(UTC*, RFC5545의 DATE-TIME형식(예: 2022-05-17T112:00:00Z)
    private String location; //장소를 어떻게 저장하지? 사용할 map api보고 생각해야할 듯 우선은 주소적는걸로
    private String sDetail;


    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getsDetail() {
        return sDetail;
    }

    public void setsDetail(String sDetail) {
        this.sDetail = sDetail;
    }
}
