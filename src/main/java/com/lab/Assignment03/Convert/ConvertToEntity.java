package com.lab.Assignment03.Convert;

import com.lab.Assignment03.DTO.*;
import com.lab.Assignment03.Entity.*;
import com.lab.Assignment03.Service.RoleService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertToEntity {
    @Autowired
    private RoleService roleService;
    public Users convertUser(UserDTO userDTO){
        Users users = new Users();
        users.setId(userDTO.getId());
        users.setName(userDTO.getName());
        users.setEmail(userDTO.getEmail());
        users.setPassword(userDTO.getPassword());
        users.setAddress(userDTO.getAddress());
        users.setPhone(userDTO.getPhone());
        users.setAvatar(userDTO.getAvatar());
        users.setGender(userDTO.getGender());
        users.setDescription(userDTO.getDescription());
        users.setIsActive(0);
        System.out.println(roleService.getRole(userDTO.getRoleId()));
        users.setRole(roleService.getRole(userDTO.getRoleId()));
        System.out.println(users.getRole());
        return users;
    }
    public DoctorUsers convertDoctorUser(DoctorUsersDTO doctorUsersDTO){
        DoctorUsers doctorUsers = new DoctorUsers();
        doctorUsers.setId(doctorUsersDTO.getId());
        doctorUsers.setIntroduction(doctorUsersDTO.getIntroduction());
        doctorUsers.setTrainingHistory(doctorUsersDTO.getTrainingHistory());
        doctorUsers.setAchievements(doctorUsersDTO.getAchievements());
        doctorUsers.setDoctorUser(convertUser(doctorUsersDTO.getUserDTO()));
        return doctorUsers;
    }
    public Schedules convertSchedules(SchedulesDTO schedulesDTO){
        Schedules schedules = new Schedules();
        schedules.setId(schedulesDTO.getId());
        schedules.setDate(schedulesDTO.getDate());
        schedules.setTime(schedulesDTO.getTime());
        schedules.setMaxBooking(schedulesDTO.getMaxBooking());
        schedules.setDoctorFee(schedulesDTO.getDoctorFee());
        return schedules;
    }
    public Comments convertComments(CommentsDTO commentsDTO){
        Comments comments = new Comments();
        comments.setId(commentsDTO.getId());
        comments.setTimeBooking(commentsDTO.getTimeBooking());
        comments.setDateBooking(commentsDTO.getDateBooking());
        comments.setContent(commentsDTO.getContent());
        comments.setConsultationFee(commentsDTO.getConsultationFee());
        return comments;
    }
    public Extrainfos convertExtrainfos(ExtrainfosDTO extrainfosDTO){
        Extrainfos extrainfos = new Extrainfos();
        extrainfos.setId(extrainfosDTO.getId());
        extrainfos.setHistoryBreath(extrainfosDTO.getHistoryBreath());
        extrainfos.setOldForm(extrainfosDTO.getOldForm());
        extrainfos.setSendForm(extrainfosDTO.getSendForm());
        extrainfos.setMoreInfo(extrainfosDTO.getMoreInfo());
        extrainfos.setCreateAt(LocalDateTime.now());
        return extrainfos;
    }
    public Clinics convertClinics(ClinicsDTO clinicsDTO){
        Clinics clinics = new Clinics();
        clinics.setId(clinicsDTO.getId());
        clinics.setName(clinicsDTO.getClinicName());
        clinics.setAddress(clinicsDTO.getAddress());
        clinics.setPhone(clinicsDTO.getPhone());
        clinics.setIntroductionHTML(clinicsDTO.getIntroductionHTML());
        clinics.setIntroductionMarkdown(clinicsDTO.getIntroductionMarkdown());
        clinics.setDescription(clinicsDTO.getDescription());
        clinics.setImage(clinicsDTO.getImage());
        clinics.setWorkingHours(clinicsDTO.getWorkingHours());
        clinics.setImportantNotes(clinicsDTO.getImportantNotes());
        clinics.setExaminationFee(clinicsDTO.getExaminationFee());
        clinics.setCreateAt(LocalDateTime.now());
        return clinics;
    }
    public Specialization convertSpecialization(SpecializationDTO specializationDTO){
        Specialization specialization = new Specialization();
        specialization.setId(specializationDTO.getId());
        specialization.setName(specializationDTO.getSpecializationName());
        specialization.setDescription(specializationDTO.getDescription());
        specialization.setImage(specializationDTO.getImage());
        specialization.setCreateAt(LocalDateTime.now());
        return specialization;
    }
    public Patients convertPatient(PatientsDTO patientsDTO){
        Patients patients = new Patients();
        patients.setId(patientsDTO.getId());
        patients.setReason(patientsDTO.getReason());
        patients.setDoctorUsers(convertDoctorUser(patientsDTO.getDoctorUsersDTO()));
        patients.setStatuses(convertStatuses(patientsDTO.getStatusesDTO()));
        patients.setUsers(convertUser(patientsDTO.getUserDTO()));
        patients.setCreateAt(LocalDateTime.now());
        return patients;
    }
    public Statuses convertStatuses(StatusesDTO statusesDTO){
        Statuses statuses = new Statuses();
        statuses.setId(statusesDTO.getId());
        statuses.setName(statusesDTO.getName());
        statuses.setCreateAt(statusesDTO.getCreateAt());
        statuses.setUpdatedAt(statusesDTO.getUpdateAt());
        return statuses;
    }
    public Posts convertPosts(PostDTO postDTO){
        Posts posts = new Posts();
        posts.setId(postDTO.getId());
        posts.setTitle(postDTO.getTitle());
        posts.setContentMarkdown(postDTO.getContentMarkdown());
        posts.setContentHTML(postDTO.getContentHTML());
        posts.setTimeBooking(postDTO.getTimeBooking());
        posts.setDateBooking(postDTO.getDateBooking());
        posts.setConfirmByDoctor(postDTO.getConfirmByDoctor());
        posts.setImage(postDTO.getImage());
        posts.setConsultationFee(postDTO.getConsultationFee());
        posts.setCreateAt(LocalDateTime.now());
        return posts;
    }
}
