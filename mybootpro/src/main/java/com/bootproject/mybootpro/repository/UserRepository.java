package com.bootproject.mybootpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.mybootpro.model.Board;
import com.bootproject.mybootpro.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
