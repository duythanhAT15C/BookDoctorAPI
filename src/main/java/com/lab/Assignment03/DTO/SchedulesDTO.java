package com.lab.Assignment03.DTO;

public class SchedulesDTO {
    private int id;
    private String date;
    private String time;
    private Integer maxBooking;
    private Integer sumBooking;
    private Integer doctorFee;
    private DoctorUsersDTO doctorUsersDTO;

    public SchedulesDTO() {
    }

    public SchedulesDTO(int id, String date, String time, Integer maxBooking, Integer sumBooking, Integer doctorFee, DoctorUsersDTO doctorUsersDTO) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.maxBooking = maxBooking;
        this.sumBooking = sumBooking;
        this.doctorFee = doctorFee;
        this.doctorUsersDTO = doctorUsersDTO;
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

    public Integer getMaxBooking() {
        return maxBooking;
    }

    public void setMaxBooking(Integer maxBooking) {
        this.maxBooking = maxBooking;
    }

    public Integer getSumBooking() {
        return sumBooking;
    }

    public void setSumBooking(Integer sumBooking) {
        this.sumBooking = sumBooking;
    }

    public DoctorUsersDTO getDoctorUsersDTO() {
        return doctorUsersDTO;
    }

    public void setDoctorUsersDTO(DoctorUsersDTO doctorUsersDTO) {
        this.doctorUsersDTO = doctorUsersDTO;
    }

    public Integer getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(Integer doctorFee) {
        this.doctorFee = doctorFee;
    }
}
