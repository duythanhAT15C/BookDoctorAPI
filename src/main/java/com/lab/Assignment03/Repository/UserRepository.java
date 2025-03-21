package com.lab.Assignment03.Repository;

import com.lab.Assignment03.Entity.DoctorUsers;
import com.lab.Assignment03.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
    Users findById(int id);
    boolean existsByEmail(String email);
}
