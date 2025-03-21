package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "reason")
    private String reason;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId")
    private DoctorUsers doctorUsers;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    private Statuses statuses;
    @OneToMany(mappedBy = "patients", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Extrainfos> extrainfos;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId")
    private Users users;
    @OneToMany(mappedBy = "patientUsers", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Comments> comments;

    public Patients() {
    }

    public Patients(String reason, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, DoctorUsers doctorUsers, Statuses statuses, List<Extrainfos> extrainfos, Users users, List<Comments> comments) {
        this.reason = reason;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.doctorUsers = doctorUsers;
        this.statuses = statuses;
        this.extrainfos = extrainfos;
        this.users = users;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DoctorUsers getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(DoctorUsers doctorUsers) {
        this.doctorUsers = doctorUsers;
    }

    public Statuses getStatuses() {
        return statuses;
    }

    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Extrainfos> getExtrainfos() {
        return extrainfos;
    }

    public void setExtrainfos(List<Extrainfos> extrainfos) {
        this.extrainfos = extrainfos;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }


}
