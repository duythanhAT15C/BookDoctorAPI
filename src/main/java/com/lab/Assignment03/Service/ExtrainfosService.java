package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.ExtrainfosDTO;

public interface ExtrainfosService {
    public boolean save(ExtrainfosDTO extrainfosDTO);
    public ExtrainfosDTO getExtrainfosDTOByPatientId(int patientId);
}
