package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.PatientsDTO;
import com.lab.Assignment03.DTO.UserDTO;
import com.lab.Assignment03.Entity.Users;

import java.util.List;

public interface UserService {
    public void save(UserDTO userDTO);
    public boolean authenticate(String email, String password);
    public boolean isEmailExists(String email);
    public boolean updatePassword(String email, String newPassword);
    public UserDTO findByEmail(String email);
    public int lockAndUnlockAccountPatients(int id);
    public boolean ckeckIsValidUser(String email);
    public UserDTO findById(int id);
    public boolean update(int id, UserDTO userDTO);
}
