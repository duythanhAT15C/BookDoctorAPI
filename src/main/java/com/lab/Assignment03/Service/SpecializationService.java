package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.Entity.Specialization;

import java.util.List;

public interface SpecializationService {
    public List<SpecializationDTO> getFeaturedSpecializations();
    List<SpecializationDTO> getByCategory(String category);
    public SpecializationDTO getSpecializationById(int id);
    public boolean save(SpecializationDTO specializationDTO);
}
