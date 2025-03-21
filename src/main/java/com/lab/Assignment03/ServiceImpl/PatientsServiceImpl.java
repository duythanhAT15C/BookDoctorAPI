package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.*;
import com.lab.Assignment03.Entity.*;
import com.lab.Assignment03.Repository.*;
import com.lab.Assignment03.Service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientsServiceImpl implements PatientsService {
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private DoctorUsersRepository doctorUsersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private DoctorUsersService doctorUsersService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StatusesService statusesService;
    @Autowired
    private PostsRepository postsRepository;
    @Override
    @Transactional
    public List<PatientsDTO> getPatientsByEmail(String email) {
        List<Object[]> result = patientsRepository.findPatientsByEmail(email);
        List<PatientsDTO> featuredSpecializations = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
                // Lấy các trường từ mảng kết quả
                Patients patients = (Patients) obj[0];
                DoctorUsers doctorUsers = (DoctorUsers) obj[1];
                Users patientUsers = (Users) obj[2];
                // Tạo DTO từ Specialization và totalBooking
                PatientsDTO dto = new PatientsDTO(patients.getId(), patients.getReason(), convertToDTO.convertDoctorUsers(doctorUsers), convertToDTO.convertUser(patientUsers));
                featuredSpecializations.add(dto);
            }
        }

        return featuredSpecializations;
    }

    @Override
    @Transactional
    public List<PatientsDTO> getPatientsByDoctorId(int id) {
        List<PatientsDTO> patientsDTOS = new ArrayList<>();
        for(Patients patients : patientsRepository.findAllPatientsWithDoctorUsers(id)){
//            PostDTO postDTO = convertToDTO.convertPost(postsRepository.getPostsByDoctorUsersIdAndWriterId(id, patients.getUsers().getId()));
//
//            if(postDTO.getConfirmByDoctor() == 1 && patients.getDeletedAt() == null){
//                patientsDTOS.add(convertToDTO.convertPatient(patients));
//            }
            StatusesDTO statusesDTO = statusesService.getStatusesDTOByPatientId(patients.getId());
            if(!statusesDTO.getName().equals("SUCCESS") && !statusesDTO.getName().equals("CANCELLED")){
                patientsDTOS.add(convertToDTO.convertPatient(patients));
            }
        }
        return patientsDTOS;
    }

    @Override
    public PatientsDTO getPatientsByDoctorIdAndPatientId(int doctorId, int patientId) {
        return convertToDTO.convertPatient(patientsRepository.getPatient(doctorId, patientId));
    }

    @Override
    @Transactional
    public boolean save(PostDTO postDTO, int id) {
        Patients patients = new Patients();
        Posts posts = convertToEntity.convertPosts(postDTO);
        Statuses statuses = new Statuses();
        statuses.setName("Pending");
        statuses.setCreateAt(LocalDateTime.now());
        Posts posts1 = postsRepository.getPostsByDoctorUsersIdAndPostId(postDTO.getDoctorUsersDTO().getId(), id);
        DoctorUsers doctorUser = null;
        Users patientUser = null;
        if(posts1 != null){
            doctorUser = doctorUsersRepository.getDoctorUser(posts1.getDoctorUsers().getId());
            patientUser = userRepository.findById(posts1.getUsers().getId());
            if(isExistsPatient(doctorUser.getId(), patientUser.getId())){
                return false;
            }
            else {
                patients.setDoctorUsers(doctorUser);
                patients.setUsers(patientUser);
                patients.setStatuses(statuses);
                patients.setReason(posts.getTitle());
                patients.setCreateAt(LocalDateTime.now());
            }
        }
        else {
            patients.setDoctorUsers(doctorUser);
            patients.setUsers(patientUser);
            patients.setStatuses(statuses);
            patients.setReason(posts.getTitle());
            patients.setCreateAt(LocalDateTime.now());
        }
        patientsRepository.save(patients);
        return true;
    }

    @Override
    public PatientsDTO getPatientsDTO(int id) {
        PatientsDTO patientsDTO = convertToDTO.convertPatient(patientsRepository.findById(id));
        return patientsDTO;
    }

    @Override
    public Patients getPatients(int id) {
        return patientsRepository.findById(id);
    }

    @Override
    public Patients getByDoctorIdAndPatientId(int doctorUserId, int patientUserId) {
        return patientsRepository.getPatient(doctorUserId, patientUserId);
    }

    @Override
    public boolean isExistsPatient(int doctorUserId, int patientUserId) {
        Patients patients = getByDoctorIdAndPatientId(doctorUserId, patientUserId);
        if(patients != null){
            return true;
        }
        return false;
    }

    @Override
    public PatientsDTO getPatientsDTOByPatientsId(int patientId) {
        Patients patients = patientsRepository.getPatientByPatientId(patientId);
        if(patients == null){
            return null;
        }
        return convertToDTO.convertPatient(patients);
    }

    @Override
    public void deletePatient(PatientsDTO patientsDTO) {
        Patients patients = convertToEntity.convertPatient(patientsDTO);
        patientsRepository.delete(patients);
    }
}
