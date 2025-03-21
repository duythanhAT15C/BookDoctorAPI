package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Clinics;
import com.lab.Assignment03.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    @Query("SELECT c FROM Comments c WHERE c.doctorUsers.id = :id")
    List<Comments> getComments(@Param("id") int id);

    Comments findById(int id);

    @Query("SELECT c FROM Comments c WHERE c.doctorUsers.id = :id")
    Comments getDoctorUsers(@Param("id") int id);

    @Query("SELECT c FROM Comments c WHERE c.doctorUsers.id = :doctorUserId AND c.patientUsers.id = :patientId")
    Comments getComments(@Param("doctorUserId") int doctorUserId, @Param("patientId") int patientId);

    @Query("SELECT c FROM Comments c WHERE c.doctorUsers.id = :doctorUserId AND c.id = :commentId")
    Comments getCommentsByIdAndDoctorId(@Param("doctorUserId") int doctorUserId, @Param("commentId") int commentId);

}
