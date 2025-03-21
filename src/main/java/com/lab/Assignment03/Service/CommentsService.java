package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.CommentsDTO;
import com.lab.Assignment03.Entity.Comments;

import java.util.List;

public interface CommentsService {
    public boolean save(CommentsDTO commentsDTO);
    public List<Comments> getListCommentsDTO(int id);
    public boolean updateStatus(int doctorUserId, int parentUserId, int check);
    public CommentsDTO getCommentDTO(int id);
    public Comments getCommentByCommentId(int id, int doctorId);
    public Comments getCommentByDoctorIdAndPatientId(int doctorUserId, int patientUserId);
}
