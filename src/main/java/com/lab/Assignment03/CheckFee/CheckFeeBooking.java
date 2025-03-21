package com.lab.Assignment03.CheckFee;

import com.lab.Assignment03.DTO.CommentsDTO;
import com.lab.Assignment03.DTO.PostDTO;
import com.lab.Assignment03.DTO.SchedulesDTO;
import com.lab.Assignment03.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckFeeBooking {
    @Autowired
    private ScheduleService scheduleService;
    public boolean checkFee(PostDTO postDTO){
        SchedulesDTO schedulesDTO = scheduleService.getSchedulesDTO(postDTO.getDoctorUsersDTO().getId());
        boolean check = false;
        if(schedulesDTO.getDoctorFee() <= postDTO.getConsultationFee()){
            check = true;
        }
        return check;
    }
}
