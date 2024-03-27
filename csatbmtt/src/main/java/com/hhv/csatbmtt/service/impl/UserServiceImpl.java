package com.hhv.csatbmtt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hhv.csatbmtt.dto.UserDTO;
import com.hhv.csatbmtt.entity.UserDetail;
import com.hhv.csatbmtt.entity.UserEntity;
import com.hhv.csatbmtt.repository.UserRepository;
import com.hhv.csatbmtt.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;

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
			if(optional.get().getPassword().equals(dto.getPassword())) {
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
