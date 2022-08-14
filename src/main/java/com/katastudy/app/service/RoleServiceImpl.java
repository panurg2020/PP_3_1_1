package com.katastudy.app.service;

import com.katastudy.app.model.Role;
import com.katastudy.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

   // @Override
  //  public Role getRole(String role) {
   //     return roleRepository.getRoleByName(role);
   // }

    public Role getAdminRole() {
        return roleRepository.findByNameIgnoreCase("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));
    }

    public Role getUserRole() {
        return roleRepository.findByNameIgnoreCase("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
    }

}
