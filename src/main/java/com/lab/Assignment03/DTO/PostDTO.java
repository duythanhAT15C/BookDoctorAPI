package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public class PostDTO {
    private int id;
    private String title;
    private String contentMarkdown;
    private String contentHTML;
    private String timeBooking;
    private String dateBooking;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private int confirmByDoctor;
    private String image;
    private int consultationFee;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime updatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime deletedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private DoctorUsersDTO doctorUsersDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private SpecializationDTO specializationDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private ClinicsDTO clinicsDTO;
    private UserDTO userDTO;

    public PostDTO() {
    }

    public PostDTO(int id, String title, String contentMarkdown, String contentHTML, String timeBooking, String dateBooking, int confirmByDoctor, String image, int consultationFee, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, DoctorUsersDTO doctorUsersDTO, SpecializationDTO specializationDTO, ClinicsDTO clinicsDTO, UserDTO userDTO) {
        this.id = id;
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
        this.doctorUsersDTO = doctorUsersDTO;
        this.specializationDTO = specializationDTO;
        this.clinicsDTO = clinicsDTO;
        this.userDTO = userDTO;
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

    public DoctorUsersDTO getDoctorUsersDTO() {
        return doctorUsersDTO;
    }

    public void setDoctorUsersDTO(DoctorUsersDTO doctorUsersDTO) {
        this.doctorUsersDTO = doctorUsersDTO;
    }

    public SpecializationDTO getSpecializationDTO() {
        return specializationDTO;
    }

    public void setSpecializationDTO(SpecializationDTO specializationDTO) {
        this.specializationDTO = specializationDTO;
    }

    public ClinicsDTO getClinicsDTO() {
        return clinicsDTO;
    }

    public void setClinicsDTO(ClinicsDTO clinicsDTO) {
        this.clinicsDTO = clinicsDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
