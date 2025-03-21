package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Integer> {
    @Query("SELECT s FROM Schedules s JOIN DoctorUsers du ON du.id = s.doctorUsers.id WHERE s.doctorUsers.id = :doctorId")
    Schedules findBySchedulesUser(@Param("doctorId") int doctorId);

}
