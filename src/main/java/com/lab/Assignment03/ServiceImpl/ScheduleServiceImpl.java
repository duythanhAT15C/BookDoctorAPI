package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.DoctorUsersDTO;
import com.lab.Assignment03.DTO.SchedulesDTO;
import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.DTO.UserDTO;
import com.lab.Assignment03.Entity.*;
import com.lab.Assignment03.Repository.ClinicsRepository;
import com.lab.Assignment03.Repository.DoctorUsersRepository;
import com.lab.Assignment03.Repository.ScheduleRepository;
import com.lab.Assignment03.Repository.UserRepository;
import com.lab.Assignment03.Service.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private DoctorUsersRepository doctorUsersRepository;
    @Autowired
    private ClinicsRepository clinicsRepository;

    @Override
    @Transactional
    public boolean save(SchedulesDTO schedulesDTO, UserDTO userDTO) {
        Schedules schedules = convertToEntity.convertSchedules(schedulesDTO);
        DoctorUsers doctorUsers = doctorUsersRepository.getDoctorUserByDoctorUserId(userDTO.getId());
        schedules.setCreateAt(LocalDateTime.now());
        schedules.setDoctorUsers(doctorUsers);
        schedules.setSumBooking(0);
        scheduleRepository.save(schedules);
        return true;
    }

    @Override
    public SchedulesDTO getSchedulesDTO(int doctorId) {
        return convertToDTO.convertSchedules(scheduleRepository.findBySchedulesUser(doctorId));
    }

    @Override
    public int updateSchedule(int id, int flag, int checkConfirmDoctor) {
        Schedules schedules = scheduleRepository.findBySchedulesUser(id);
        if(flag == 1){
            int sum = schedules.getSumBooking() + 1;
            schedules.setSumBooking(sum);
        }
        else {
            if(schedules.getSumBooking() > 0 && checkConfirmDoctor == 1){
                schedules.setSumBooking(schedules.getSumBooking() - 1);
            }
        }
        schedules.setUpdatedAt(LocalDateTime.now());
        scheduleRepository.save(schedules);
        return 1;
    }

    @Override
    public int maxBooking(int id, int flag) {
        Schedules schedules = scheduleRepository.findBySchedulesUser(id);
        if(flag == 1){
            int sum = schedules.getSumBooking() + 1;
            if(sum > schedules.getMaxBooking()){
                return -1;
            }
        }
        return 0;
    }
}
