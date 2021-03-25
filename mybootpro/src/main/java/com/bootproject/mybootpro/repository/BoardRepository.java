package com.bootproject.mybootpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.mybootpro.model.Board;



public interface BoardRepository  extends JpaRepository<Board, Long>{

}
