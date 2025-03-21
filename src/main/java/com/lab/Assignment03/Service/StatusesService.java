package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.StatusesDTO;
import com.lab.Assignment03.Entity.Statuses;

public interface StatusesService {
    public void save(StatusesDTO statusesDTO, int check);
    public StatusesDTO getStatusesDTOByPatientId(int patientId);
    public Statuses getStatusesByPatientId(int patientId);
}
