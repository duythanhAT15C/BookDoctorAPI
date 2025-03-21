package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.ClinicsDTO;
import com.lab.Assignment03.DTO.SpecializationDTO;
import com.lab.Assignment03.Entity.Clinics;
import com.lab.Assignment03.Entity.DoctorUsers;
import com.lab.Assignment03.Entity.Specialization;
import com.lab.Assignment03.Repository.ClinicsRepository;
import com.lab.Assignment03.Repository.DoctorUsersRepository;
import com.lab.Assignment03.Repository.SpecializationRepository;
import com.lab.Assignment03.Service.ClinicsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClinicsServiceImpl implements ClinicsService {
    @Autowired
    private ClinicsRepository clinicsRepository;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private SpecializationRepository specializationRepository;
    @Autowired
    private DoctorUsersRepository doctorUsersRepository;
    @Autowired
    private ConvertToEntity convertToEntity;

    @Override
    @Transactional
    public List<ClinicsDTO> getFeaturedClinics() {
        List<Object[]> result = clinicsRepository.findTopClinicsByMaxBooking();
        List<ClinicsDTO> featuredClinics = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            for (Object[] obj : result) {
                // Lấy các trường từ mảng kết quả
                Clinics clinics = (Clinics) obj[0];
                Long totalBooking = (Long) obj[1]; // Lấy totalBooking từ mảng kết quả
                List<SpecializationDTO> specializations = new ArrayList<>();
                // Lấy danh sách DoctorUser của clinic
                List<DoctorUsers> doctorUsers = doctorUsersRepository.findAllById(clinics.getId());

                // Lấy Specialization từ DoctorUser (1 clinic chỉ nằm trong 1 specialization)
                SpecializationDTO specializationDTO = null;
                if (!doctorUsers.isEmpty()) {
                    Specialization specialization = doctorUsers.get(0).getSpecialization();
                    if (specialization != null) {
                        specializationDTO = new SpecializationDTO(
                                specialization.getId(),
                                specialization.getName(),
                                specialization.getDescription(),
                                specialization.getImage(),
                                totalBooking,
                                convertToDTO.convertClinics(clinics)
                        );
                    }
                }
                // Tạo DTO từ Specialization và totalBooking
                ClinicsDTO dto = new ClinicsDTO(clinics.getId(), clinics.getName(), clinics.getAddress(), clinics.getPhone(), clinics.getDescription(), clinics.getIntroductionHTML(), clinics.getIntroductionMarkdown(), clinics.getImage() , clinics.getWorkingHours(), clinics.getImportantNotes(), clinics.getExaminationFee(), totalBooking, specializationDTO);
                featuredClinics.add(dto);
            }
        }
        return featuredClinics;
    }

    @Override
    @Transactional
    public List<ClinicsDTO> getClinicsDTOByAddress(String address) {
        List<ClinicsDTO> list = new ArrayList<>();
        for(Clinics clinics : clinicsRepository.findByAddress(address)){
            ClinicsDTO clinicsDTO = convertToDTO.convertClinics(clinics);
            list.add(clinicsDTO);
        }
        return list;
    }

    @Override
    @Transactional
    public List<ClinicsDTO> getClinicsDTOByCategory(String category) {
        List<ClinicsDTO> list = new ArrayList<>();
        for(Clinics clinics : clinicsRepository.findClinicsBySpecializationName(category)){
            ClinicsDTO clinicsDTO = convertToDTO.convertClinics(clinics);
            list.add(clinicsDTO);
        }
        return list;
    }

    @Override
    public List<ClinicsDTO> getClinicsDTOByExaminationFee(Long minExaminationFee, Long maxExaminationFee) {
        List<ClinicsDTO> list = new ArrayList<>();
        for(Clinics clinics : clinicsRepository.findByExaminationFeeBetween(minExaminationFee, maxExaminationFee)){
            ClinicsDTO clinicsDTO = convertToDTO.convertClinics(clinics);
            list.add(clinicsDTO);
        }
        return list;
    }

    @Override
    public List<ClinicsDTO> getClinicsDTOByName(String name) {
        List<ClinicsDTO> list = new ArrayList<>();
        for(Clinics clinics : clinicsRepository.findByName(name)){
            ClinicsDTO clinicsDTO = convertToDTO.convertClinics(clinics);
            list.add(clinicsDTO);
        }
        return list;
    }

    @Override
    public ClinicsDTO getClinicsDTOById(int id) {
        Clinics clinics = clinicsRepository.findById(id);
        if(clinics == null){
            return null;
        }
        ClinicsDTO clinicsDTO = convertToDTO.convertClinics(clinicsRepository.findById(id));
        return clinicsDTO;
    }

    @Override
    public boolean save(ClinicsDTO clinicsDTO) {
        Clinics clinics = convertToEntity.convertClinics(clinicsDTO);
        clinicsRepository.save(clinics);
        return true;
    }
}
