package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "contentMarkdown")
    private String contentMarkdown;
    @Column(name = "contentHTML")
    private String contentHTML;
    @Column(name = "timeBooking")
    private String timeBooking;
    @Column(name = "dateBooking")
    private String dateBooking;
    @Column(name = "confirmByDoctor")
    private int confirmByDoctor;
    @Column(name = "image")
    private String image;
    @Column(name = "consultationFee")
    private int consultationFee;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "forDoctorId")
    private DoctorUsers doctorUsers;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "forSpecializationId")
    private Specialization specialization;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "forClinicId")
    private Clinics clinics;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "writerId")
    private Users users;

    public Posts() {
    }

    public Posts(String title, String contentMarkdown, String contentHTML, String timeBooking, String dateBooking, int confirmByDoctor, String image, int consultationFee, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, DoctorUsers doctorUsers, Specialization specialization, Clinics clinics, Users users) {
        this.title = title;
        this.contentMarkdown = contentMarkdown;
        this.contentHTML = contentHTML;
        this.timeBooking = timeBooking;
        this.dateBooking = dateBooking;
        this.confirmByDoctor = confirmByDoctor;
        this.image = image;
        this.consultationFee = consultationFee;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.doctorUsers = doctorUsers;
        this.specialization = specialization;
        this.clinics = clinics;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentMarkdown() {
        return contentMarkdown;
    }

    public void setContentMarkdown(String contentMarkdown) {
        this.contentMarkdown = contentMarkdown;
    }

    public String getContentHTML() {
        return contentHTML;
    }

    public void setContentHTML(String contentHTML) {
        this.contentHTML = contentHTML;
    }

    public String getTimeBooking() {
        return timeBooking;
    }

    public void setTimeBooking(String timeBooking) {
        this.timeBooking = timeBooking;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public int getConfirmByDoctor() {
        return confirmByDoctor;
    }

    public void setConfirmByDoctor(int confirmByDoctor) {
        this.confirmByDoctor = confirmByDoctor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(int consultationFee) {
        this.consultationFee = consultationFee;
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

    public DoctorUsers getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(DoctorUsers doctorUsers) {
        this.doctorUsers = doctorUsers;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Clinics getClinics() {
        return clinics;
    }

    public void setClinics(Clinics clinics) {
        this.clinics = clinics;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
