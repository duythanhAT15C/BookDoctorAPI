package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends JpaRepository<Places, Integer> {
    Places findById(int id);
}
