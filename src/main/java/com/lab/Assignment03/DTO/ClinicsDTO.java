package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public class ClinicsDTO {
    private int id;
    private String clinicName;
    private String address;
    private String phone;
    private String description;
    private String introductionHTML;
    private String introductionMarkdown;
    private String image;
    private String workingHours;
    private String importantNotes;
    private Long examinationFee;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private Long totalBooking;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private SpecializationDTO specializationDTO;

    public ClinicsDTO() {

    }

    public ClinicsDTO(int id, String clinicName, String address, String phone, String description, String introductionHTML, String introductionMarkdown, String image, String workingHours, String importantNotes, Long examinationFee, Long totalBooking, SpecializationDTO specializationDTO) {
        this.id = id;
        this.clinicName = clinicName;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.introductionHTML = introductionHTML;
        this.introductionMarkdown = introductionMarkdown;
        this.image = image;
        this.workingHours = workingHours;
        this.importantNotes = importantNotes;
        this.examinationFee = examinationFee;
        this.totalBooking = totalBooking;
        this.specializationDTO = specializationDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Long getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Long totalBooking) {
        this.totalBooking = totalBooking;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SpecializationDTO getSpecializationDTO() {
        return specializationDTO;
    }

    public void setSpecializationDTO(SpecializationDTO specializationDTO) {
        this.specializationDTO = specializationDTO;
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
}
