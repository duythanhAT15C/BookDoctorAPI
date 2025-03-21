package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.*;
import com.lab.Assignment03.Entity.Clinics;
import com.lab.Assignment03.Entity.DoctorUsers;
import com.lab.Assignment03.Entity.Specialization;
import com.lab.Assignment03.Entity.Users;
import com.lab.Assignment03.Repository.ClinicsRepository;
import com.lab.Assignment03.Repository.DoctorUsersRepository;
import com.lab.Assignment03.Repository.SpecializationRepository;
import com.lab.Assignment03.Repository.UserRepository;
import com.lab.Assignment03.Service.DoctorUsersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class DoctorUsersServiceImpl implements DoctorUsersService {
    @Autowired
    private DoctorUsersRepository doctorUsersRepository;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClinicsRepository clinicsRepository;
    @Autowired
    private SpecializationRepository specializationRepository;
    @Override
    @Transactional
    public List<DoctorUsersDTO> listDoctorUsersById(int id) {
        List<Object[]> result = doctorUsersRepository.findDoctorUsers(id);
        List<DoctorUsersDTO> doctorUsersDTOS = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
                // Lấy các trường từ mảng kết quả
                DoctorUsersDTO doctorUsersDTO = (DoctorUsersDTO) obj[0];
                UserDTO userDTO = (UserDTO) obj[1];
                ClinicsDTO clinicsDTO = (ClinicsDTO) obj[2];
                SpecializationDTO specializationDTO = (SpecializationDTO) obj[3];
                // Tạo DTO từ Specialization và totalBooking
                DoctorUsersDTO dto = new DoctorUsersDTO(doctorUsersDTO.getId(), doctorUsersDTO.getIntroduction(), doctorUsersDTO.getTrainingHistory(), doctorUsersDTO.getAchievements(), userDTO, clinicsDTO, specializationDTO);
                doctorUsersDTOS.add(dto);
            }
        }
        return doctorUsersDTOS;
    }
    @Override
    public List<DoctorUsersDTO> getUserDoctorBySpecializationName(String specializationName) {
        List<Object[]> result = doctorUsersRepository.getDoctorUsersBySpecializationName(specializationName);
        List<DoctorUsersDTO> doctorUsersDTOS = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
                // Lấy các trường từ mảng kết quả
                DoctorUsers doctorUsers = (DoctorUsers) obj[0];
                Users users = (Users) obj[1];
                Clinics clinics = (Clinics) obj[2];
                Specialization specialization = (Specialization) obj[3];
                UserDTO userDTO = convertToDTO.convertUser(users);
                DoctorUsersDTO doctorUsersDTO = new DoctorUsersDTO();
                doctorUsersDTO.setUserDTO(userDTO);
                doctorUsersDTO.setId(doctorUsers.getId());
                doctorUsersDTO.setTrainingHistory(doctorUsers.getTrainingHistory());
                doctorUsersDTO.setAchievements(doctorUsers.getAchievements());
                doctorUsersDTO.setIntroduction(doctorUsers.getIntroduction());
                doctorUsersDTOS.add(doctorUsersDTO);
                doctorUsersDTO.setClinicsDTO(convertToDTO.convertClinics(clinics));
                doctorUsersDTO.setSpecializationDTO(convertToDTO.convertSpecializationDTO(specialization));
            }
        }
        return doctorUsersDTOS;
    }

    @Override
    @Transactional
    public void save(DoctorUsersDTO doctorUsersDTO) {
        DoctorUsers doctorUsers = convertToEntity.convertDoctorUser(doctorUsersDTO);
        doctorUsers.getDoctorUser().setPassword(passwordEncoder.encode(doctorUsersDTO.getUserDTO().getPassword()));
        doctorUsers.getDoctorUser().setIsActive(1);
        doctorUsers.setCreateAt(LocalDateTime.now());
        if(!userRepository.existsByEmail(doctorUsersDTO.getUserDTO().getEmail())){
            doctorUsers.getDoctorUser().setCreateAt(LocalDateTime.now());
        }
        else {
            doctorUsers.getDoctorUser().setUpdatedAt(LocalDateTime.now());
        }
        Clinics clinics = clinicsRepository.findById(doctorUsersDTO.getClinicsDTO().getId());
        Specialization specialization = specializationRepository.findById(doctorUsersDTO.getSpecializationDTO().getId());
        doctorUsers.setClinics(clinics);
        doctorUsers.setSpecialization(specialization);
        doctorUsersRepository.save(doctorUsers);
    }

    @Override
    public DoctorUsersDTO getDoctorUserById(int id) {
        DoctorUsers doctorUsers = doctorUsersRepository.getDoctorUser(id);
        if(doctorUsers == null){
            return null;
        }
        return convertToDTO.convertDoctorUsers(doctorUsers);
    }

    @Override
    public void update(UserDTO userDTO, DoctorUsersDTO doctorUsersDTO) {
        Users users = userRepository.findById(userDTO.getId());
        DoctorUsers doctorUsers = doctorUsersRepository.getDoctorUserByDoctorUserId(users.getId());
        if(doctorUsersDTO.getUserDTO() != null){
            users.setName(doctorUsersDTO.getUserDTO().getName());
            users.setEmail(doctorUsersDTO.getUserDTO().getEmail());
            users.setAddress(doctorUsersDTO.getUserDTO().getAddress());
            users.setPhone(doctorUsersDTO.getUserDTO().getPhone());
            users.setAvatar(doctorUsersDTO.getUserDTO().getAvatar());
            users.setGender(doctorUsersDTO.getUserDTO().getGender());
            users.setDescription(doctorUsersDTO.getUserDTO().getDescription());
            doctorUsers.setIntroduction(doctorUsersDTO.getIntroduction());
            doctorUsers.setTrainingHistory(doctorUsersDTO.getTrainingHistory());
            doctorUsers.setAchievements(doctorUsersDTO.getAchievements());
            users.setUpdatedAt(LocalDateTime.now());
            doctorUsers.setDoctorUser(users);
        }
        else {
            doctorUsers.setIntroduction(doctorUsersDTO.getIntroduction());
            doctorUsers.setTrainingHistory(doctorUsersDTO.getTrainingHistory());
            doctorUsers.setAchievements(doctorUsersDTO.getAchievements());
        }
        doctorUsers.setUpdatedAt(LocalDateTime.now());
        doctorUsersRepository.save(doctorUsers);
    }

    @Override
    public DoctorUsersDTO getDoctorUsersByEmail(String email) {
        DoctorUsersDTO findDoctorUser = doctorUsersRepository.findDoctorByEmail(email);
        if(findDoctorUser == null){
            return null;
        }
        return findDoctorUser;
    }

    @Override
    public DoctorUsersDTO getDoctorByDoctorUsersId(int doctorUsersId) {
        DoctorUsers doctorUsers = doctorUsersRepository.getDoctorUserByDoctorUserId(doctorUsersId);
        if(doctorUsers == null){
            return null;
        }
        return convertToDTO.convertDoctorUsers(doctorUsers);
    }
}
