package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Clinics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClinicsRepository extends JpaRepository<Clinics, Integer> {
    @Query("SELECT c, " +
            "SUM(sc.sumBooking) AS totalBooking " +
            "FROM Schedules sc " +
            "JOIN DoctorUsers du ON sc.doctorUsers.id = du.id " +  // Liên kết với Users
            "JOIN Clinics c ON du.clinicsDoctorUsers.id = c.id " +  // Liên kết với Specialization
            "WHERE sc.deletedAt IS NULL " +
            "GROUP BY c " +
            "HAVING SUM(sc.sumBooking) > 0 " +  // Thêm GROUP BY
            "ORDER BY totalBooking DESC") // Nhóm theo các trường của Specialization
    List<Object[]> findTopClinicsByMaxBooking();

    @Query("SELECT c FROM Clinics c WHERE c.address LIKE %:address%")
    List<Clinics> findByAddress(@Param("address") String address);

    @Query("SELECT c FROM Clinics c " +
            "JOIN c.specializations s " + // Liên kết với chuyên khoa qua quan hệ trong entity Clinics
            "WHERE s.name LIKE %:specializationName%")
    List<Clinics> findClinicsBySpecializationName(@Param("specializationName") String specializationName);


    List<Clinics> findByExaminationFeeBetween(Long minExaminationFee, Long maxExaminationFee);

    @Query("SELECT c FROM Clinics c WHERE c.name LIKE %:name%")
    List<Clinics> findByName(@Param("name") String name);

    Clinics findById(int id);
}
