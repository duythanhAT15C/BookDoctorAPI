package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.DoctorUsersDTO;
import com.lab.Assignment03.DTO.SchedulesDTO;
import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.DTO.UserDTO;
import com.lab.Assignment03.Entity.Schedules;

import java.util.List;

public interface ScheduleService {
    public boolean save(SchedulesDTO schedulesDTO, UserDTO userDTO);
    public SchedulesDTO getSchedulesDTO(int doctorId);
    public int updateSchedule(int id, int flag, int checkConfirmDoctor);
    public int maxBooking(int id, int flag);
}
