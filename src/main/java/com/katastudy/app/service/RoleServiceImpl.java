package com.katastudy.app.service;

import org.springframework.stereotype.Service;
import com.katastudy.app.repository.RoleRepository;
import com.katastudy.app.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public Set<Role> getAllRoles() {
        Set<Role> roles = new HashSet<>();
        roles.addAll(roleRepository.findAll());
        return roles;
    }
}
