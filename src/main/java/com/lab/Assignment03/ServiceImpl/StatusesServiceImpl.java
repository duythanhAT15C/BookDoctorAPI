package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.StatusesDTO;
import com.lab.Assignment03.Entity.Statuses;
import com.lab.Assignment03.Repository.StatusesRepository;
import com.lab.Assignment03.Service.StatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StatusesServiceImpl implements StatusesService {
    @Autowired
    private StatusesRepository statusesRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Override
    public void save(StatusesDTO statusesDTO, int check) {
        Statuses statuses = convertToEntity.convertStatuses(statusesDTO);
        if(check == 1){
            statuses.setName("REBOOKED");
            statuses.setUpdatedAt(LocalDateTime.now());
        } else if (check == 0) {
            statuses.setName("CANCELLED");
            statuses.setDeletedAt(LocalDateTime.now());
        }
        statusesRepository.save(statuses);
    }

    @Override
    public StatusesDTO getStatusesDTOByPatientId(int patientId) {
        Statuses statuses = statusesRepository.findByPatientByStatusIdAndPatientId(patientId);
        if(statuses == null){
            return null;
        }
        return convertToDTO.convertStatusesDTO(statuses);
    }

    @Override
    public Statuses getStatusesByPatientId(int patientId) {
        return statusesRepository.findByPatientByStatusIdAndPatientId(patientId);
    }
}
