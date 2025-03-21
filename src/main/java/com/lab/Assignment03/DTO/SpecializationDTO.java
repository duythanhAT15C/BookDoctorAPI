package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lab.Assignment03.Entity.Specialization;

import java.time.LocalDateTime;

public class SpecializationDTO {
    private int id;
    private String specializationName;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String image;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private Long totalBooking;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private ClinicsDTO clinicsDTO;

    public SpecializationDTO() {
    }

    public SpecializationDTO(int id, String specializationName, String description, String image, Long totalBooking, ClinicsDTO clinicsDTO) {
        this.id = id;
        this.specializationName = specializationName;
        this.description = description;
        this.image = image;
        this.totalBooking = totalBooking;
        this.clinicsDTO = clinicsDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
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

    public Long getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Long totalBooking) {
        this.totalBooking = totalBooking;
    }

    public ClinicsDTO getClinicsDTO() {
        return clinicsDTO;
    }

    public void setClinicsDTO(ClinicsDTO clinicsDTO) {
        this.clinicsDTO = clinicsDTO;
    }
}
