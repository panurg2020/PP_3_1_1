package com.katastudy.app.repository;

import com.katastudy.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   // @Query(value = "SELECT r FROM Role r WHERE r.name = :name")
    //Role getRoleByName(String name);
   Optional<Role> findByNameIgnoreCase(@Param("name") String name);
}