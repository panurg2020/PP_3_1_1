package com.katastudy.app.service;

import com.katastudy.app.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();
    void saveRole(Role role);
    Role getRoleById(Long id);
    void deleteRoleById(Long id);
  //  Role getRole(String role);
    Role getAdminRole();

    Role getUserRole();

}


