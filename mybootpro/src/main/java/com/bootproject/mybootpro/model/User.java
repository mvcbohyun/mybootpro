package com.bootproject.mybootpro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	private Boolean enabled;
	
	@ManyToMany
	@JoinTable(
	  name = "user_role", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)//사용자가 변경이 되면 보드도 연결된 데이터 같이 저장 
	//FetchType {EAGER가 기본값인것 = (OneToOne,ManyToOne) LAZY가 기본값인것 = (OneToMany,ManyToMay)}
	// EAGER -- User을 가져올때 board도 같이 가져온다 -- 성능 떨어짐 , 
	// LAZY  -- 나중에 board 가 조회 될때 가져옴 
//	@OneToMany(mappedBy = "user", fetch = FetchType.)
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL		)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)//사용자가 변경이 되면 보드도 연결된 데이터 같이 저장
	private List<Board> boards= new ArrayList<>();
}
