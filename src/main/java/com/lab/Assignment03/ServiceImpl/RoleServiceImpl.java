package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Entity.Role;
import com.lab.Assignment03.Repository.RoleRepository;
import com.lab.Assignment03.Service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    @Transactional
    public Role getRole(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Không tồn tại role với id: " + id));
    }
}
