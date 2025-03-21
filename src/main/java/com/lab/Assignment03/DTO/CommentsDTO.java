package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lab.Assignment03.Entity.Patients;

public class CommentsDTO {
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private PatientsDTO patientsDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private DoctorUsersDTO doctorUsersDTO;
    private String timeBooking;
    private String dateBooking;
    private String content;
    private int consultationFee;

    public CommentsDTO() {
    }

    public CommentsDTO(int id, PatientsDTO patientsDTO, DoctorUsersDTO doctorUsersDTO, String timeBooking, String dateBooking, String content, int consultationFee) {
        this.id = id;
        this.patientsDTO = patientsDTO;
        this.doctorUsersDTO = doctorUsersDTO;
        this.timeBooking = timeBooking;
        this.dateBooking = dateBooking;
        this.content = content;
        this.consultationFee = consultationFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PatientsDTO getPatientsDTO() {
        return patientsDTO;
    }

    public void setPatientsDTO(PatientsDTO patientsDTO) {
        this.patientsDTO = patientsDTO;
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

    public int getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(int consultationFee) {
        this.consultationFee = consultationFee;
    }

    public DoctorUsersDTO getDoctorUsersDTO() {
        return doctorUsersDTO;
    }

    public void setDoctorUsersDTO(DoctorUsersDTO doctorUsersDTO) {
        this.doctorUsersDTO = doctorUsersDTO;
    }
}
