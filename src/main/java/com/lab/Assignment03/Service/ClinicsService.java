package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.ClinicsDTO;
import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.Entity.Clinics;

import java.util.List;

public interface ClinicsService {
    public List<ClinicsDTO> getFeaturedClinics();
    public List<ClinicsDTO> getClinicsDTOByAddress(String address);
    public List<ClinicsDTO> getClinicsDTOByCategory(String category);
    public List<ClinicsDTO> getClinicsDTOByExaminationFee(Long minExaminationFee, Long maxExaminationFee);
    public List<ClinicsDTO> getClinicsDTOByName(String name);
    public ClinicsDTO getClinicsDTOById(int id);
    public boolean save(ClinicsDTO clinicsDTO);
}
