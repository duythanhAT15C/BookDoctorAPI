package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Comments;
import com.lab.Assignment03.Entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {
    @Query("SELECT p FROM Posts p WHERE p.doctorUsers.id = :id")
    List<Posts> getPostsByDoctorUsersId(@Param("id") int id);
    @Query("SELECT p FROM Posts p WHERE p.doctorUsers.id = :doctorUserId AND p.id = :postsId")
    Posts getPostsByDoctorUsersIdAndPostId(@Param("doctorUserId") int doctorUserId, @Param("postsId") int postsId);
    Posts findById(int id);
    @Query("SELECT p FROM Posts p WHERE p.doctorUsers.id = :doctorUserId AND p.users.id = :writerId")
    Posts getPostsByDoctorUsersIdAndWriterId(@Param("doctorUserId") int doctorUserId, @Param("writerId") int writerId);
}
