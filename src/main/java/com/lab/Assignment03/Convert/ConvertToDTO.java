package com.lab.Assignment03.Convert;

import com.lab.Assignment03.DTO.*;
import com.lab.Assignment03.Entity.*;
import com.lab.Assignment03.Repository.ExtrainfosRepository;
import com.lab.Assignment03.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class ConvertToDTO {
    @Autowired
    private ExtrainfosRepository extrainfosRepository;
    public PatientsDTO convertPatient(Patients patients){
        PatientsDTO patientsDTO = new PatientsDTO();
        Extrainfos extrainfosDTOS = extrainfosRepository.getExtrainfosByPatientId(patients.getId());
        UserDTO userDTO = new UserDTO();
        patientsDTO.setId(patients.getId());
        userDTO.setId(patients.getUsers().getId());
        userDTO.setName(patients.getUsers().getName());
        userDTO.setEmail(patients.getUsers().getEmail());
        userDTO.setAddress(patients.getUsers().getAddress());
        userDTO.setPhone(patients.getUsers().getPhone());
        userDTO.setAvatar(patients.getUsers().getAvatar());
        userDTO.setGender(patients.getUsers().getGender());
        userDTO.setDescription(patients.getUsers().getDescription());
        userDTO.setRoleId(patients.getUsers().getRole().getId());
        patientsDTO.setUserDTO(userDTO);
        patientsDTO.setReason(patients.getReason());
        patientsDTO.setStatusesDTO(convertStatusesDTO(patients.getStatuses()));
        return patientsDTO;
    }
    public ClinicsDTO convertClinics(Clinics clinics){
        ClinicsDTO clinicsDTO = new ClinicsDTO();
        clinicsDTO.setId(clinics.getId());
        clinicsDTO.setClinicName(clinics.getName());
        clinicsDTO.setAddress(clinics.getAddress());
        clinicsDTO.setPhone(clinics.getPhone());
        clinicsDTO.setDescription(clinics.getDescription());
        clinicsDTO.setImage(clinics.getImage());
        clinicsDTO.setWorkingHours(clinics.getWorkingHours());
        clinicsDTO.setImportantNotes(clinics.getImportantNotes());
        clinicsDTO.setExaminationFee(clinics.getExaminationFee());
        return clinicsDTO;
    }
    public UserDTO convertUser(Users users){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setName(users.getName());
        userDTO.setEmail(users.getEmail());
        userDTO.setAddress(users.getAddress());
        userDTO.setPhone(users.getPhone());
        userDTO.setAvatar(users.getAvatar());
        userDTO.setGender(users.getGender());
        userDTO.setDescription(users.getDescription());
//        userDTO.setIsActive(users.getIsActive());
        userDTO.setRoleId(users.getRole().getId());
        return userDTO;
    }
    public DoctorUsersDTO convertDoctorUsers(DoctorUsers doctorUsers){
        DoctorUsersDTO doctorUsersDTO = new DoctorUsersDTO();
        doctorUsersDTO.setId(doctorUsers.getId());
        doctorUsersDTO.setIntroduction(doctorUsers.getIntroduction());
        doctorUsersDTO.setTrainingHistory(doctorUsers.getTrainingHistory());
        doctorUsersDTO.setAchievements(doctorUsers.getAchievements());
        doctorUsersDTO.setUserDTO(convertUser(doctorUsers.getDoctorUser()));
        doctorUsersDTO.setClinicsDTO(convertClinics(doctorUsers.getClinicsDoctorUsers()));
        doctorUsersDTO.setSpecializationDTO(convertSpecializationDTO(doctorUsers.getSpecialization()));
        return doctorUsersDTO;
    }

    public SchedulesDTO convertSchedules(Schedules schedules){
        SchedulesDTO schedulesDTO = new SchedulesDTO();
        schedulesDTO.setId(schedules.getId());
        schedulesDTO.setDate(schedules.getDate());
        schedulesDTO.setTime(schedules.getTime());
        schedulesDTO.setMaxBooking(schedules.getMaxBooking());
        schedulesDTO.setSumBooking(schedules.getSumBooking());
        schedulesDTO.setDoctorFee(schedules.getDoctorFee());
        return schedulesDTO;
    }
    public CommentsDTO convertCommentDTO(Comments comments){
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setId(comments.getId());
        commentsDTO.setTimeBooking(comments.getTimeBooking());
        commentsDTO.setDateBooking(comments.getDateBooking());
        commentsDTO.setContent(comments.getContent());
        commentsDTO.setConsultationFee(comments.getConsultationFee());
        return commentsDTO;
    }
    public SpecializationDTO convertSpecializationDTO(Specialization specialization){
        SpecializationDTO specializationDTO = new SpecializationDTO();
        specializationDTO.setId(specialization.getId());
        specializationDTO.setSpecializationName(specialization.getName());
        specializationDTO.setDescription(specialization.getDescription());
        specializationDTO.setImage(specialization.getImage());
        specializationDTO.setClinicsDTO(convertClinics(specialization.getClinics()));
        return specializationDTO;
    }
    public StatusesDTO convertStatusesDTO(Statuses statuses){
        StatusesDTO statusesDTO = new StatusesDTO();
        statusesDTO.setId(statuses.getId());
        statusesDTO.setName(statuses.getName());
        statusesDTO.setCreateAt(statuses.getCreateAt());
        statusesDTO.setUpdateAt(statuses.getUpdatedAt());
        return statusesDTO;
    }
    public PostDTO convertPost(Posts posts){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(posts.getId());
        postDTO.setTitle(posts.getTitle());
        postDTO.setContentMarkdown(posts.getContentMarkdown());
        postDTO.setContentHTML(posts.getContentHTML());
        postDTO.setTimeBooking(posts.getTimeBooking());
        postDTO.setDateBooking(posts.getDateBooking());
//        postDTO.setDoctorUsersDTO(convertDoctorUsers(posts.getDoctorUsers()));
//        postDTO.setSpecializationDTO(convertSpecializationDTO(posts.getSpecialization()));
//        postDTO.setClinicsDTO(convertClinics(posts.getClinics()));
        postDTO.setUserDTO(convertUser(posts.getUsers()));
        postDTO.setImage(posts.getImage());
        postDTO.setConsultationFee(posts.getConsultationFee());
        postDTO.setConfirmByDoctor(posts.getConfirmByDoctor());
        return postDTO;
    }
    public ExtrainfosDTO convertExtrainfos(Extrainfos extrainfos){
        ExtrainfosDTO extrainfosDTO = new ExtrainfosDTO();
        extrainfosDTO.setId(extrainfos.getId());
        extrainfosDTO.setHistoryBreath(extrainfos.getHistoryBreath());
        extrainfosDTO.setOldForm(extrainfos.getOldForm());
        extrainfosDTO.setSendForm(extrainfos.getSendForm());
        extrainfosDTO.setMoreInfo(extrainfos.getMoreInfo());
        extrainfosDTO.setPatientsDTO(convertPatient(extrainfos.getPatients()));
        return extrainfosDTO;
    }
    public PlacesDTO convertPlace(Places places){
        PlacesDTO placesDTO = new PlacesDTO();
        placesDTO.setId(places.getId());
        placesDTO.setName(places.getName());
        return placesDTO;
    }
}
