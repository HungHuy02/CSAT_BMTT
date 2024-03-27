package com.hhv.csatbmtt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhv.csatbmtt.dto.PermissionDTO;
import com.hhv.csatbmtt.dto.UserDTO;
import com.hhv.csatbmtt.service.PermissionService;
import com.hhv.csatbmtt.service.UserService;
import com.hhv.csatbmtt.util.JwtUtil;

@RestController
@RequestMapping("api/user")
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired 
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
		UserDTO response1 = userService.login(userDTO);
		if(response1.getSuccess()) {
			HttpHeaders header = new HttpHeaders();
			header.add("Authorization", "Bearer " + jwtUtil.generateToken(userDTO.getId(), userDTO.getPassword()));
//			header.add("Authen", jwtUtil.generateToken(userDTO.getId(), userDTO.getPassword()));
			return new ResponseEntity<UserDTO>(response1 , header, HttpStatus.OK);
		}else
			return new ResponseEntity<UserDTO>(response1 , HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("updateDecentralization/:{id}")
	public ResponseEntity<PermissionDTO> updatePermission(@PathVariable String id) {
		return null;
	}
	
	
	@GetMapping("getDataUser/getDecentralization/:{id}")
	public ResponseEntity<List<UserDTO>> getAllUser(@PathVariable String id) {
		return new ResponseEntity<List<UserDTO>>(userService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("logout")
	public ResponseEntity<UserDTO> logout() {
		return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);
	}
	
	@GetMapping("getDecentralization")
	public ResponseEntity<PermissionDTO> getDecentralization(@RequestBody UserDTO dto) {
		PermissionDTO response = permissionService.findAll();
		if(response.getSuccess()) {
			return new ResponseEntity<PermissionDTO>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<PermissionDTO>(response, HttpStatus.BAD_REQUEST);

		}
		
	}
	
//	@PostMapping("generateToken") 
//    public String authenticateAndGetToken(@RequestBody UserDTO authRequest) { 
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getId(), authRequest.getPassword())); 
//        if (authentication.isAuthenticated()) { 
//            return jwtUtil.generateToken(authRequest.getId()); 
//        } else { 
//            throw new UsernameNotFoundException("invalid user request !"); 
//        } 
//    } 
}
