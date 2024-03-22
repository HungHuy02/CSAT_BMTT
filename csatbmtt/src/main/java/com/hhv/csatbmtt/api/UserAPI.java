package com.hhv.csatbmtt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhv.csatbmtt.dto.UserDTO;
import com.hhv.csatbmtt.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("register")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
		UserDTO response = userService.register(userDTO);
		if(response.getSuccess()) {
			return new ResponseEntity<UserDTO>(response , HttpStatus.OK);
		}else
			return new ResponseEntity<UserDTO>(response , HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
		UserDTO response = userService.login(userDTO);
		if(response.getSuccess()) {
			return new ResponseEntity<UserDTO>(response , HttpStatus.OK);
		}else
			return new ResponseEntity<UserDTO>(response , HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("getDataUser")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return new ResponseEntity<List<UserDTO>>(userService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("logout")
	public ResponseEntity<UserDTO> logout() {
		return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);
	}
}
