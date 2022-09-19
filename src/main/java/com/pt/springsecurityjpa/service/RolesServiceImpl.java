package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Role;
import com.pt.springsecurityjpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.get();
    }

    @Override
    public void deleteRoleById(long id) {
        roleRepository.deleteById(id);
    }
}
