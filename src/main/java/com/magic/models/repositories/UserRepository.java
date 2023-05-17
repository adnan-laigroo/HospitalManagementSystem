package com.magic.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magic.models.User;

public interface UserRepository extends JpaRepository<User, String> {

}