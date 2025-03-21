package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.DoctorUsersDTO;
import com.lab.Assignment03.DTO.UserDTO;

import java.util.List;

public interface DoctorUsersService {
    public List<DoctorUsersDTO> listDoctorUsersById(int id);
    public List<DoctorUsersDTO> getUserDoctorBySpecializationName(String specializationName);
    public void save(DoctorUsersDTO doctorUsersDTO);
    public DoctorUsersDTO getDoctorUserById(int id);
    public void update(UserDTO userDTO, DoctorUsersDTO doctorUsersDTO);
    public DoctorUsersDTO getDoctorUsersByEmail(String email);
    public DoctorUsersDTO getDoctorByDoctorUsersId(int doctorUsersId);
}
