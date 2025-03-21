package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clinics")
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "introductionHTML")
    private String introductionHTML;
    @Column(name = "introductionMarkdown")
    private String introductionMarkdown;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "workingHours")
    private String workingHours;
    @Column(name = "importantNotes")
    private String importantNotes;
    @Column(name = "examinationFee")
    private Long examinationFee;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy = "clinicsDoctorUsers", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<DoctorUsers> doctorUsers;
    @OneToMany(mappedBy = "clinics", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Specialization> specializations;

    public Clinics() {
    }

    public Clinics(String name, String address, String phone, String introductionHTML, String introductionMarkdown, String description, String image, String workingHours, String importantNotes, Long examinationFee, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, List<DoctorUsers> doctorUsers, List<Specialization> specializations) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.introductionHTML = introductionHTML;
        this.introductionMarkdown = introductionMarkdown;
        this.description = description;
        this.image = image;
        this.workingHours = workingHours;
        this.importantNotes = importantNotes;
        this.examinationFee = examinationFee;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.doctorUsers = doctorUsers;
        this.specializations = specializations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroductionHTML() {
        return introductionHTML;
    }

    public void setIntroductionHTML(String introductionHTML) {
        this.introductionHTML = introductionHTML;
    }

    public String getIntroductionMarkdown() {
        return introductionMarkdown;
    }

    public void setIntroductionMarkdown(String introductionMarkdown) {
        this.introductionMarkdown = introductionMarkdown;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<DoctorUsers> getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(List<DoctorUsers> doctorUsers) {
        this.doctorUsers = doctorUsers;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getImportantNotes() {
        return importantNotes;
    }

    public void setImportantNotes(String importantNotes) {
        this.importantNotes = importantNotes;
    }

    public Long getExaminationFee() {
        return examinationFee;
    }

    public void setExaminationFee(Long examinationFee) {
        this.examinationFee = examinationFee;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }
}
