package com.hhv.csatbmtt.service;

import java.util.List;

import com.hhv.csatbmtt.dto.UserDTO;

public interface UserService {
	
	
	UserDTO save(UserDTO dto);
	List<UserDTO> findAll();
	UserDTO register(UserDTO dto);
	UserDTO login(UserDTO dto);
}
