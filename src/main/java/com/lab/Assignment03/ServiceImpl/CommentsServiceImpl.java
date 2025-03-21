package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.CommentsDTO;
import com.lab.Assignment03.Entity.Comments;
import com.lab.Assignment03.Entity.DoctorUsers;
import com.lab.Assignment03.Entity.Patients;
import com.lab.Assignment03.Entity.Users;
import com.lab.Assignment03.Repository.CommentsRepository;
import com.lab.Assignment03.Repository.DoctorUsersRepository;
import com.lab.Assignment03.Repository.PatientsRepository;
import com.lab.Assignment03.Repository.UserRepository;
import com.lab.Assignment03.Service.CommentsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private DoctorUsersRepository doctorUsersRepository;
    @Autowired
    private ConvertToDTO convertToDTO;

    @Override
    @Transactional
    public boolean save(CommentsDTO commentsDTO) {
        Comments comments = convertToEntity.convertComments(commentsDTO);
        comments.setCreateAt(LocalDateTime.now());
        Patients patients = patientsRepository.findById(commentsDTO.getPatientsDTO().getId());
        comments.setPatientUsers(patients);
        DoctorUsers doctorUser = doctorUsersRepository.getDoctorUser(commentsDTO.getDoctorUsersDTO().getUserDTO().getId());
        comments.setDoctorUsers(doctorUser);
        comments.setStatus(-1);
        commentsRepository.save(comments);
        return true;
    }

    @Override
    public List<Comments> getListCommentsDTO(int id) {
        List<Comments> list = commentsRepository.getComments(id);
        List<Comments> listComments = new ArrayList<>();
        for (Comments comments : list) {
            if (comments.getStatus() == 1) {
                listComments.add(comments);
            }
        }
        return listComments;
    }

    @Override
    @Transactional
    public boolean updateStatus(int doctorUserId, int parentUserId, int check) {
        Comments comments = commentsRepository.getComments(doctorUserId, parentUserId);
        if (comments == null) {
            return false;
        } else {
            if(comments.getStatus() == 1 && check == 1){
                return false;
            }
            if(comments.getStatus() == 0 && check == 0){
                return false;
            }
            if(check == 1){
                comments.setStatus(1);
            }
            else if (check == 0){
                comments.setStatus(0);
            }
            comments.setUpdatedAt(LocalDateTime.now());
            commentsRepository.save(comments);
        }
        return true;

    }

    @Override
    public CommentsDTO getCommentDTO(int id) {
        return convertToDTO.convertCommentDTO(commentsRepository.findById(id));
    }

    @Override
    public Comments getCommentByCommentId(int id, int doctorId) {
        return commentsRepository.getCommentsByIdAndDoctorId(doctorId, id);
    }

    @Override
    public Comments getCommentByDoctorIdAndPatientId(int doctorUserId, int patientUserId) {
        return commentsRepository.getComments(doctorUserId, patientUserId);
    }

}
