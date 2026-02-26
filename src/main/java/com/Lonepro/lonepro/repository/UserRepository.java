package com.Lonepro.lonepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Lonepro.lonepro.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
