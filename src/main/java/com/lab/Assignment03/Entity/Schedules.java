package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "maxBooking")
    private int maxBooking;
    @Column(name = "sumBooking")
    private int sumBooking;
    @Column(name = "doctorFee")
    private Integer doctorFee;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId")
    private DoctorUsers doctorUsers;


    public Schedules() {
    }

    public Schedules(String date, String time, int maxBooking, int sumBooking, Integer doctorFee, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, DoctorUsers doctorUsers) {
        this.date = date;
        this.time = time;
        this.maxBooking = maxBooking;
        this.sumBooking = sumBooking;
        this.doctorFee = doctorFee;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.doctorUsers = doctorUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMaxBooking() {
        return maxBooking;
    }

    public void setMaxBooking(int maxBooking) {
        this.maxBooking = maxBooking;
    }

    public int getSumBooking() {
        return sumBooking;
    }

    public void setSumBooking(int sumBooking) {
        this.sumBooking = sumBooking;
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


    public Integer getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(Integer doctorFee) {
        this.doctorFee = doctorFee;
    }

    public DoctorUsers getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(DoctorUsers doctorUsers) {
        this.doctorUsers = doctorUsers;
    }

    @Override
    public String toString() {
        return "Schedules{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", maxBooking='" + maxBooking + '\'' +
                ", sumBooking='" + sumBooking + '\'' +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", users=" + doctorUsers +
                '}';
    }
}
