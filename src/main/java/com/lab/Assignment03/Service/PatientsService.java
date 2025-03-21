package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.CommentsDTO;
import com.lab.Assignment03.DTO.PatientsDTO;
import com.lab.Assignment03.DTO.PostDTO;
import com.lab.Assignment03.Entity.Patients;

import java.util.List;

public interface PatientsService {
    public List<PatientsDTO> getPatientsByEmail(String email);
    public List<PatientsDTO> getPatientsByDoctorId(int id);
    public PatientsDTO getPatientsByDoctorIdAndPatientId(int doctorId, int patientId);
    public boolean save(PostDTO postDTO, int patientId);
    public PatientsDTO getPatientsDTO(int id);
    public Patients getPatients(int id);
    public Patients getByDoctorIdAndPatientId(int doctorUserId, int patientUserId);
    public boolean isExistsPatient(int doctorUserId, int patientUserId);
    public PatientsDTO getPatientsDTOByPatientsId(int patientId);
    public void deletePatient(PatientsDTO patientsDTO);
}
