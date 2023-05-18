package com.magic.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.User;

public interface UserRepository extends JpaRepository<User, String> {

}