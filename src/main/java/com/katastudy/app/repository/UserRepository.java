package com.katastudy.app.repository;

import com.katastudy.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    User findByUserName(String userName);
    User getUserById (Long id);
}
