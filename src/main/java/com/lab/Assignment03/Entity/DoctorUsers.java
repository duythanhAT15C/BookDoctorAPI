package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doctor_users")
public class DoctorUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "trainingHistory")
    private String trainingHistory;
    @Column(name = "achievements")
    private String achievements;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId")
    private Users doctorUser;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinicId")
    private Clinics clinicsDoctorUsers;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "specializationId")
    private Specialization specialization;
    @OneToMany(mappedBy = "doctorUsers", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Schedules> doctorUsers;
    @OneToMany(mappedBy = "doctorUsers", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Comments>  doctorComment;


    public DoctorUsers() {
    }

    public DoctorUsers(String introduction, String trainingHistory, String achievements, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Users doctorUser, Clinics clinicsDoctorUsers, Specialization specialization) {
        this.introduction = introduction;
        this.trainingHistory = trainingHistory;
        this.achievements = achievements;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.doctorUser = doctorUser;
        this.clinicsDoctorUsers = clinicsDoctorUsers;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Users getDoctorUser() {
        return doctorUser;
    }

    public void setDoctorUser(Users doctorUser) {
        this.doctorUser = doctorUser;
    }

    public Clinics getClinics() {
        return clinicsDoctorUsers;
    }

    public void setClinics(Clinics clinicsDoctorUsers) {
        this.clinicsDoctorUsers = clinicsDoctorUsers;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }


    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(String trainingHistory) {
        this.trainingHistory = trainingHistory;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public Clinics getClinicsDoctorUsers() {
        return clinicsDoctorUsers;
    }

    public void setClinicsDoctorUsers(Clinics clinicsDoctorUsers) {
        this.clinicsDoctorUsers = clinicsDoctorUsers;
    }

    public List<Schedules> getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(List<Schedules> doctorUsers) {
        this.doctorUsers = doctorUsers;
    }

    public List<Comments> getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(List<Comments> doctorComment) {
        this.doctorComment = doctorComment;
    }
}
