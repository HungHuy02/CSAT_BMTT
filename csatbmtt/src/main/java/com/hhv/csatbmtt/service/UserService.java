package com.hhv.csatbmtt.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hhv.csatbmtt.dto.UserDTO;

public interface UserService extends UserDetailsService{
	
	UserDTO findById(UserDTO dto);
	UserDTO save(UserDTO dto);
	List<UserDTO> findAll();
	UserDTO register(UserDTO dto);
	UserDTO login(UserDTO dto);
}
