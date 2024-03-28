package com.hhv.csatbmtt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hhv.csatbmtt.dto.UserDTO;
import com.hhv.csatbmtt.entity.UserDetail;
import com.hhv.csatbmtt.entity.UserEntity;
import com.hhv.csatbmtt.repository.UserRepository;
import com.hhv.csatbmtt.security.RSA;
import com.hhv.csatbmtt.service.UserService;
import com.hhv.csatbmtt.util.EncryptAndDecryptUtil;
import com.hhv.csatbmtt.util.JwtUtil;

import lombok.Builder;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RSA rsa;
	
	@Autowired
	private EncryptAndDecryptUtil encryptAndDecryptUtil;
	
	@Autowired 
	private JwtUtil jwtUtil;

	@Override
	public UserDTO save(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		List<UserDTO> dtos = new ArrayList<>();
		List<UserEntity> entities = repository.findAll();
		for (UserEntity userEntity : entities) {
			UserDTO dto = mapper.map(userEntity, UserDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public UserDTO register(UserDTO dto) {
		UserDTO response = new UserDTO();
		if(repository.existsById(dto.getCitizenIdentificationNumber()) || repository.existsByAtm(dto.getAtm())
				|| repository.existsByEmail(dto.getEmail()) || repository.existsByPhoneNumber(dto.getPhoneNumber())) {
			response.setSuccess(false);
			response.setMes("Thất bại");
		}else {
			UserEntity entity = encryptAndDecryptUtil.encryptAll(mapper.map(dto, UserEntity.class));
			Map<String, String> keys = rsa.generateKey();
			entity.setPublicKey(keys.get("public_key"));
			entity.setPrivateKey(keys.get("private_key"));
			repository.save(entity);
			response.setSuccess(true);
			response.setMes("Thành công");
		}
		return response;
	}

	@Override
	public UserDTO login(UserDTO dto) {
		UserDTO response = new UserDTO();
		Optional<UserEntity> optional = repository.findById(dto.getCitizenIdentificationNumber());
		if(optional.isEmpty()) {
			response.setSuccess(false);
			response.setMes("Thất bại");
		}else {
			if(encryptAndDecryptUtil.checkPassword(optional.get(), dto)) {
				response.setCitizenIdentificationNumber(optional.get().getCitizenIdentificationNumber());
				response.setToken(jwtUtil.generateToken(dto.getCitizenIdentificationNumber(), dto.getPassword()));
				response.setSuccess(true);
				response.setMes("Thành công");
			}else {
				response.setSuccess(false);
				response.setMes("Thất bại");
			}
		}
		return response;
	}

	@Override
	public UserDTO findById(UserDTO dto) {
		UserDTO response;
		dto.setPassword(jwtUtil.extractPass(dto.getToken()));
		Optional<UserEntity> entity = repository.findByCitizenIdentificationNumber(dto.getCitizenIdentificationNumber());
		if(entity.isEmpty()) {
			response = UserDTO.builder().success(false).build();
		}else {
			UserDTO decrypt = encryptAndDecryptUtil.decryptAll(entity.get() ,dto);
			response = UserDTO.builder().individual(decrypt).success(true).build();
		}
		return response;
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<UserEntity> entity = repository.findByCitizenIdentificationNumber(id);
		return entity.map(UserDetail::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + id));
	}

}
