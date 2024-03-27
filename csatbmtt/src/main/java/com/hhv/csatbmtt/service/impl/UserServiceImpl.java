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
		if(repository.existsById(dto.getId()) || repository.existsByAtm(dto.getAtm())
				|| repository.existsByEmail(dto.getEmail()) || repository.existsByPhone(dto.getPhone())) {
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
		Optional<UserEntity> optional = repository.findById(dto.getId());
		if(optional.isEmpty()) {
			response.setSuccess(false);
			response.setMes("Thất bại");
		}else {
			if(encryptAndDecryptUtil.checkPassword(optional.get(), dto)) {
				response.setId(optional.get().getId());
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
		Optional<UserEntity> entity = repository.findById(dto.getId());
		if(entity.isEmpty()) {
			response = UserDTO.builder().success(false).build();
		}else {
			response = UserDTO.builder()
					.id(entity.get().getId())
					.password(entity.get().getPassword())
					.success(true)
					.build();
		}
		return response;
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<UserEntity> entity = repository.findById(id);
		return entity.map(UserDetail::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + id));
	}

}
