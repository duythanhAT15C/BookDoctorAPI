package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "timeBooking")
    private String timeBooking;
    @Column(name = "dateBooking")
    private String dateBooking;
    @Column(name = "content")
    private String content;
    @Column(name = "consultationFee")
    private int consultationFee;
    @Column(name = "status")
    private Integer status;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId")
    private Patients patientUsers;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId")
    private DoctorUsers doctorUsers;

    public Comments() {
    }

    public Comments(String timeBooking, String dateBooking, String content, int consultationFee, Integer status, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Patients patientUsers, DoctorUsers doctorUsers) {
        this.timeBooking = timeBooking;
        this.dateBooking = dateBooking;
        this.content = content;
        this.consultationFee = consultationFee;
        this.status = status;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.patientUsers = patientUsers;
        this.doctorUsers = doctorUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Patients getPatientUsers() {
        return patientUsers;
    }

    public void setPatientUsers(Patients patientUsers) {
        this.patientUsers = patientUsers;
    }

    public int getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(int consultationFee) {
        this.consultationFee = consultationFee;
    }

    public DoctorUsers getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(DoctorUsers doctorUsers) {
        this.doctorUsers = doctorUsers;
    }
}
