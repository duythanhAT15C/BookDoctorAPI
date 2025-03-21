package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.ClinicsDTO;
import com.lab.Assignment03.DTO.DoctorUsersDTO;
import com.lab.Assignment03.DTO.UserDTO;
import com.lab.Assignment03.Entity.DoctorUsers;
import com.lab.Assignment03.Entity.Users;
import com.lab.Assignment03.Exception.UserException;
import com.lab.Assignment03.Repository.UserRepository;
import com.lab.Assignment03.Service.UserService;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ConvertToDTO convertToDTO;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        Users users = convertToEntity.convertUser(userDTO);
        logger.info("User object: {}", users);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setIsActive(1);
        users.setCreateAt(LocalDateTime.now());
        userRepository.save(users);
    }

    @Override
    public boolean authenticate(String email, String rawpassword) {
        Users users = userRepository.findByEmail(email);
        if(users == null){
            return false;
        }
        return passwordEncoder.matches(rawpassword, users.getPassword());
    }

    @Override
    @Transactional
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        Users users = userRepository.findByEmail(email);
        if(users == null){
            return false;
        }
        users.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(users);
        return true;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return convertToDTO.convertUser(userRepository.findByEmail(email));
    }


    @Override
    public int lockAndUnlockAccountPatients(int id) {
        Users users = userRepository.findById(id);
        int status = 0;
        if(users == null){
            status = -2;
        }
        else {
            if(users.getRole().getId() == 2 || users.getRole().getId() == 3){
                if (users.getIsActive() == 0) {
                    users.setIsActive(1); // Kích hoạt
                    status = 1;
                } else {
                    users.setIsActive(0); // Vô hiệu hoá
                }
                userRepository.save(users);
            }
            else {
                status = -1;
            }
        }
        return status;
    }

    @Override
    public boolean ckeckIsValidUser(String email) {
        Users users = userRepository.findByEmail(email);
        if(users.getIsActive() == 0){
            return false;
        }
        return true;
    }

    @Override
    public UserDTO findById(int id) {
        if(userRepository.findById(id) == null){
            return null;
        }
        return convertToDTO.convertUser(userRepository.findById(id));
    }

    @Override
    public boolean update(int id, UserDTO userDTO) {
        Users users = userRepository.findById(id);
        if(users != null){
            users.setName(userDTO.getName());
            users.setEmail(userDTO.getEmail());
            users.setAddress(userDTO.getAddress());
            users.setPhone(userDTO.getPhone());
            users.setAvatar(userDTO.getAvatar());
            users.setGender(userDTO.getGender());
            users.setDescription(userDTO.getDescription());
            users.setUpdatedAt(LocalDateTime.now());
            userRepository.save(users);
            return true;
        }
        return false;
    }
}
