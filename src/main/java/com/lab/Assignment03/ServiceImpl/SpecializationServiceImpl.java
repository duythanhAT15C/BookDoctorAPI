package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.Entity.Clinics;
import com.lab.Assignment03.Entity.Specialization;
import com.lab.Assignment03.Repository.ClinicsRepository;
import com.lab.Assignment03.Repository.SpecializationRepository;
import com.lab.Assignment03.Service.SpecializationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService {
    @Autowired
    private SpecializationRepository specializationRepository;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ClinicsRepository clinicsRepository;

    @Override
    @Transactional
    public List<SpecializationDTO> getFeaturedSpecializations() {
        List<Object[]> result = specializationRepository.findTopSpecializationsByMaxBooking();
        List<SpecializationDTO> featuredSpecializations = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
                // Lấy các trường từ mảng kết quả
                Specialization specialization = (Specialization) obj[0];
                Long totalBooking = (Long) obj[1]; // Lấy totalBooking từ mảng kết quả
                Clinics clinics = clinicsRepository.findById(specialization.getClinics().getId());
                // Tạo DTO từ Specialization và totalBooking
                SpecializationDTO dto = new SpecializationDTO(specialization.getId(), specialization.getName(), specialization.getDescription(), specialization.getImage(), totalBooking, convertToDTO.convertClinics(clinics));
                featuredSpecializations.add(dto);
            }
        }
        return featuredSpecializations;
    }

    @Override
    public List<SpecializationDTO> getByCategory(String category) {
        return null;
    }

    @Override
    public SpecializationDTO getSpecializationById(int id) {
        Specialization specialization = specializationRepository.findById(id);
        if (specialization == null) {
            return null;
        }
        SpecializationDTO specializationDTO = convertToDTO.convertSpecializationDTO(specializationRepository.findById(id));
        return specializationDTO;
    }

    @Override
    public boolean save(SpecializationDTO specializationDTO) {
        Specialization specialization = convertToEntity.convertSpecialization(specializationDTO);
        if (specialization == null) {
            return false;
        }
        Clinics clinics = clinicsRepository.findById(specializationDTO.getClinicsDTO().getId());
        specialization.setClinics(clinics);
        specializationRepository.save(specialization);
        return true;
    }
}
