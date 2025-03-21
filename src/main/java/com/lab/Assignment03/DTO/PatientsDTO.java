package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class PatientsDTO {
    private int id;
    private String reason;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    public DoctorUsersDTO doctorUsersDTO;
    public UserDTO userDTO;
    private StatusesDTO statusesDTO;


    public PatientsDTO() {
    }

    public PatientsDTO(int id, String reason, DoctorUsersDTO doctorUsersDTO, UserDTO userDTO) {
        this.id = id;
        this.reason = reason;
        this.doctorUsersDTO = doctorUsersDTO;
        this.userDTO = userDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DoctorUsersDTO getDoctorUsersDTO() {
        return doctorUsersDTO;
    }

    public void setDoctorUsersDTO(DoctorUsersDTO doctorUsersDTO) {
        this.doctorUsersDTO = doctorUsersDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public StatusesDTO getStatusesDTO() {
        return statusesDTO;
    }

    public void setStatusesDTO(StatusesDTO statusesDTO) {
        this.statusesDTO = statusesDTO;
    }
}
