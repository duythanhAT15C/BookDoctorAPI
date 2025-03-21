package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public class ExtrainfosDTO {
    private int id;
    private PatientsDTO patientsDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String historyBreath;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private PlacesDTO placesDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String oldForm;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String sendForm;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String moreInfo;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime updateAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime deleteAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String reasonForVisit;   // Lý do khám
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String symptoms;         // Triệu chứng
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String diagnosis;        // Chẩn đoán
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String treatmentPlan;    // Hướng điều trị
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String labResults;    // Kết quả xét nghiệm
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String doctorAdvice;  // Kết luận & Hướng dẫn điều trị

    public ExtrainfosDTO() {
    }

    public ExtrainfosDTO(int id, PatientsDTO patientsDTO, String historyBreath, PlacesDTO placesDTO, String oldForm, String sendForm, String moreInfo, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.id = id;
        this.patientsDTO = patientsDTO;
        this.historyBreath = historyBreath;
        this.placesDTO = placesDTO;
        this.oldForm = oldForm;
        this.sendForm = sendForm;
        this.moreInfo = moreInfo;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public ExtrainfosDTO(String historyBreath, String oldForm, String sendForm, String moreInfo) {
        this.historyBreath = historyBreath;
        this.oldForm = oldForm;
        this.sendForm = sendForm;
        this.moreInfo = moreInfo;
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

    public String getHistoryBreath() {
        return historyBreath;
    }

    public void setHistoryBreath(String historyBreath) {
        this.historyBreath = historyBreath;
    }

    public PlacesDTO getPlacesDTO() {
        return placesDTO;
    }

    public void setPlacesDTO(PlacesDTO placesDTO) {
        this.placesDTO = placesDTO;
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getLabResults() {
        return labResults;
    }

    public void setLabResults(String labResults) {
        this.labResults = labResults;
    }

    public String getDoctorAdvice() {
        return doctorAdvice;
    }

    public void setDoctorAdvice(String doctorAdvice) {
        this.doctorAdvice = doctorAdvice;
    }

    @Override
    public String toString() {
        return "ExtrainfosDTO{" +
                "id=" + id +
                ", patientsDTO=" + patientsDTO +
                ", historyBreath='" + historyBreath + '\'' +
                ", placesDTO=" + placesDTO +
                ", oldForm='" + oldForm + '\'' +
                ", sendForm='" + sendForm + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                '}';
    }
}
