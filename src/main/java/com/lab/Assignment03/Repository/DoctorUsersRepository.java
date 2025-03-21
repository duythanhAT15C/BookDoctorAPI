package com.lab.Assignment03.Repository;

import com.lab.Assignment03.DTO.DoctorUsersDTO;
import com.lab.Assignment03.DTO.PatientsDTO;
import com.lab.Assignment03.DTO.UserDTO;
import com.lab.Assignment03.Entity.DoctorUsers;
import com.lab.Assignment03.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
@Repository
public interface DoctorUsersRepository extends JpaRepository<DoctorUsers, Integer> {
    @Query("SELECT du, u, c, sp FROM DoctorUsers du " +
            "JOIN Users u ON du.doctorUser.id = u.id " +  // Liên kết với Users
            "JOIN Role r ON u.role.id = r.id " +  // Liên kết với Users
            "JOIN Clinics c ON c.id = du.clinicsDoctorUsers.id " +  // Liên kết với Users
            "JOIN Specialization sp ON sp.id = du.specialization.id " +  // Liên kết với Users
            "WHERE du.deletedAt IS NULL AND r.id = 3 AND du.id = :id")
    List<Object[]> findDoctorUsers(@Param("id") int id);

    @Query("SELECT du, u, c, s FROM DoctorUsers du JOIN Specialization s ON s.id = du.specialization.id " +
            "JOIN Users u ON u.id = du.doctorUser.id " +
            "JOIN Clinics c ON c.id = du.specialization.id " +
            "where s.name LIKE %:specializationName%")
    List<Object[]> getDoctorUsersBySpecializationName(String specializationName);

    @Query("SELECT du FROM DoctorUsers du WHERE du.id = :id")
    DoctorUsers getDoctorUser(@Param("id") int id);

    @Query("SELECT du, u " +
            "FROM DoctorUsers du " +
            "JOIN Users u ON du.doctorUser.id = u.id " +  // Liên kết với Users
            "WHERE du.deletedAt IS NULL AND u.email = :email ")
    DoctorUsersDTO findDoctorByEmail(@Param("email") String email);

    @Query("SELECT du FROM DoctorUsers du WHERE du.doctorUser.id = :doctorUsersId")
    DoctorUsers getDoctorUserByDoctorUserId(@Param("doctorUsersId") int doctorUsersId);
    List<DoctorUsers> findAllById(int id);
}
