package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Statuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusesRepository extends JpaRepository<Statuses, Integer> {
    @Query("SELECT st FROM Statuses st JOIN Patients p ON p.statuses.id = st.id WHERE p.id = :patientId")
    Statuses findByPatientByStatusIdAndPatientId(@Param("patientId") int patientId);
}
