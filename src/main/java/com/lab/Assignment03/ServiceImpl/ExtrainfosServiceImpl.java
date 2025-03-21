package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.ExtrainfosDTO;
import com.lab.Assignment03.Entity.Extrainfos;
import com.lab.Assignment03.Entity.Patients;
import com.lab.Assignment03.Entity.Places;
import com.lab.Assignment03.Repository.ExtrainfosRepository;
import com.lab.Assignment03.Repository.PatientsRepository;
import com.lab.Assignment03.Repository.PlacesRepository;
import com.lab.Assignment03.Service.ExtrainfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtrainfosServiceImpl implements ExtrainfosService {
    @Autowired
    private ExtrainfosRepository extrainfosRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private PlacesRepository placesRepository;
    @Override
    public boolean save(ExtrainfosDTO extrainfosDTO) {
        Extrainfos extrainfos = convertToEntity.convertExtrainfos(extrainfosDTO);
        Patients patients = patientsRepository.findById(extrainfosDTO.getPatientsDTO().getId());
        if(patients == null){
            return false;
        }
        extrainfos.setPatients(patients);
        extrainfosRepository.save(extrainfos);
        return true;
    }

    @Override
    public ExtrainfosDTO getExtrainfosDTOByPatientId(int patientId) {
        Extrainfos extrainfos = extrainfosRepository.getExtrainfosByPatientId(patientId);
        if(extrainfos == null){
            return null;
        }
        return convertToDTO.convertExtrainfos(extrainfos);
    }
}
