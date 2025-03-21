package com.lab.Assignment03.DTO;

import jakarta.persistence.Column;

public class DoctorUsersDTO {
    private int id;
    private String introduction;
    private String trainingHistory;
    private String achievements;
    private UserDTO userDTO;
    private ClinicsDTO clinicsDTO;
    private SpecializationDTO specializationDTO;

    public DoctorUsersDTO() {
    }

    public DoctorUsersDTO(int id, String introduction, String trainingHistory, String achievements, UserDTO userDTO, ClinicsDTO clinicsDTO, SpecializationDTO specializationDTO) {
        this.id = id;
        this.introduction = introduction;
        this.trainingHistory = trainingHistory;
        this.achievements = achievements;
        this.userDTO = userDTO;
        this.clinicsDTO = clinicsDTO;
        this.specializationDTO = specializationDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(String trainingHistory) {
        this.trainingHistory = trainingHistory;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public ClinicsDTO getClinicsDTO() {
        return clinicsDTO;
    }

    public void setClinicsDTO(ClinicsDTO clinicsDTO) {
        this.clinicsDTO = clinicsDTO;
    }

    public SpecializationDTO getSpecializationDTO() {
        return specializationDTO;
    }

    public void setSpecializationDTO(SpecializationDTO specializationDTO) {
        this.specializationDTO = specializationDTO;
    }
}
