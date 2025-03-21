package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientsRepository extends JpaRepository<Patients, Integer> {
    @Query("SELECT p, du, u " +
            "FROM Patients p " +
            "JOIN Users u ON p.id = u.id " +  // Liên kết với Users
            "JOIN DoctorUsers du ON du.id = p.users.id " +  // Liên kết với Users
            "WHERE p.deletedAt IS NULL AND u.email = :email ")
    List<Object[]> findPatientsByEmail(@Param("email") String email);

    Patients findById(int id);

    @Query("SELECT du, u FROM DoctorUsers du " +
            "JOIN Users u ON du.doctorUser.id = u.id " +  // Liên kết với Users
            "JOIN Role r ON u.role.id = r.id " +  // Liên kết với Users
            "WHERE du.deletedAt IS NULL AND r.id = 3 AND du.id = :id")
    List<Object[]> findDoctorUsers(@Param("id") int id);

    @Query("SELECT p FROM Patients p JOIN DoctorUsers du ON p.doctorUsers.id = du.id where du.id = :id")
    List<Patients> findAllPatientsWithDoctorUsers(@Param("id") int id);

    @Query("SELECT p, st FROM Patients p JOIN Statuses st ON st.id = p.statuses.id WHERE p.doctorUsers.id = :doctorUserId AND p.users.id = :patientId")
    Patients getPatient(@Param("doctorUserId") int doctorUserId, @Param("patientId") int patientId);

    @Query("SELECT p FROM Patients p WHERE p.users.id = :patientId")
    Patients getPatientByPatientId(@Param("patientId") int patientId);
}
