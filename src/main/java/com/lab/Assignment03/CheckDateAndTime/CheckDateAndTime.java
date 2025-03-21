package com.lab.Assignment03.CheckDateAndTime;

import com.lab.Assignment03.DTO.CommentsDTO;
import com.lab.Assignment03.DTO.PostDTO;
import com.lab.Assignment03.DTO.SchedulesDTO;
import com.lab.Assignment03.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Component
public class CheckDateAndTime {
    @Autowired
    private FormatTime formatTime;
    @Autowired
    private ScheduleService scheduleService;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public boolean checkDate(String dateBooking, String dateDb){
        LocalDate userDate = LocalDate.parse(dateBooking, dateFormat);

        // So sánh với ngày trong database (chuyển string dbDateStr thành LocalDate)
        LocalDate dbDate = LocalDate.parse(dateDb, dateFormat);
        if(userDate.isAfter(dbDate)){
            return false;
        }
        return true;
    }
    public boolean startCheckDate(PostDTO postDTO){
        SchedulesDTO schedulesDTO = scheduleService.getSchedulesDTO(postDTO.getDoctorUsersDTO().getId());
        boolean check = false;
        if(checkDate(postDTO.getDateBooking(), schedulesDTO.getDate())){
            check = true;
        }
        return check;
    }
    public boolean checkTime(String timeBooking, String timeDb){
        LocalTime userTime = formatTime.parseTime(timeBooking);

        // So sánh với ngày trong database (chuyển string dbDateStr thành LocalDate)
        LocalTime dbTime = LocalTime.parse(timeDb);
        if(userTime.isAfter(dbTime)){
            return false;
        }
        return true;
    }
    public boolean startCheckTime(PostDTO postDTO){
        SchedulesDTO schedulesDTO = scheduleService.getSchedulesDTO(postDTO.getDoctorUsersDTO().getId());
        boolean check = false;
        if(checkTime(postDTO.getTimeBooking(), schedulesDTO.getTime())){
            check = true;
        }
        return check;
    }
}
