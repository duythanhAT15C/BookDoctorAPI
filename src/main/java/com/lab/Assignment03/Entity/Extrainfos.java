package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "extrainfos")
public class Extrainfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "historyBreath")
    private String historyBreath;
    @Column(name = "oldForms")
    private String oldForm;
    @Column(name = "sendForms")
    private String sendForm;
    @Column(name = "moreInfo")
    private String moreInfo;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId")
    private Patients patients;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "placeId")
    private Places places;

    public Extrainfos() {
    }

    public Extrainfos(String historyBreath, String oldForm, String sendForm, String moreInfo, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Patients patients, Places places) {
        this.historyBreath = historyBreath;
        this.oldForm = oldForm;
        this.sendForm = sendForm;
        this.moreInfo = moreInfo;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.patients = patients;
        this.places = places;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHistoryBreath() {
        return historyBreath;
    }

    public void setHistoryBreath(String historyBreath) {
        this.historyBreath = historyBreath;
    }

    public String getOldForm() {
        return oldForm;
    }

    public void setOldForm(String oldForm) {
        this.oldForm = oldForm;
    }

    public String getSendForm() {
        return sendForm;
    }

    public void setSendForm(String sendForm) {
        this.sendForm = sendForm;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
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

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }
}
