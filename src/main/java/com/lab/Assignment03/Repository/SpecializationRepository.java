package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Clinics;
import com.lab.Assignment03.Entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    @Query("SELECT s FROM Specialization s WHERE s.name LIKE %:category%")
    List<Specialization> findByNameContaining(@Param("category") String category);

    Specialization findById(int id);
    @Query("SELECT s, SUM(sc.sumBooking) AS totalBooking " +
            "FROM Schedules sc " +
            "JOIN DoctorUsers du ON sc.doctorUsers.id = du.id " +  // Liên kết với DoctorUser
            "JOIN Specialization s ON du.specialization.id = s.id " +  // Liên kết với Specialization
            "WHERE sc.deletedAt IS NULL " +
            "GROUP BY s.id " +
            "HAVING SUM(sc.sumBooking) > 0 " +  // Thêm GROUP BY
            "ORDER BY totalBooking DESC") // Nếu muốn sắp xếp theo tổng số đặt lịch
    List<Object[]> findTopSpecializationsByMaxBooking();
}
