package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Extrainfos;
import com.lab.Assignment03.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtrainfosRepository extends JpaRepository<Extrainfos, Integer> {
    @Query("SELECT ex FROM Extrainfos ex WHERE ex.patients.id = :patientId")
    Extrainfos getExtrainfosByPatientId(@Param("patientId") int patientId);
}
