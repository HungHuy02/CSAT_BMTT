package com.hhv.csatbmtt.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhv.csatbmtt.dto.PermissionDTO;
import com.hhv.csatbmtt.entity.PermissionEntity;
import com.hhv.csatbmtt.repository.PermissionRepository;
import com.hhv.csatbmtt.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public PermissionDTO findAll() {
		PermissionDTO response;
		List<PermissionEntity> entities = repository.findAll();
		if(entities.isEmpty()) {
			response = PermissionDTO.builder()
					.mes("Thất bại")
					.success(false)
					.build();
		}else {
			response = PermissionDTO.builder()
					.mes("Thành công")
					.success(true)
					.dtos(entities.stream()
							.map(entity -> mapper.map(entity, PermissionDTO.class))
							.collect(Collectors.toList()))
					.build();
		}
		return response;
	}

	@Override
	public PermissionDTO findByIdMain(String idMain) {
		PermissionDTO response;
		List<PermissionEntity> entities = repository.findAll();
		if(entities.isEmpty()) {
			response = PermissionDTO.builder()
					.mes("Thất bại")
					.success(false)
					.build();
		}else {
			response = PermissionDTO.builder()
					.mes("Thành công")
					.success(true)
					.dtos(entities.stream()
							.map(entity -> mapper.map(entity, PermissionDTO.class))
							.collect(Collectors.toList()))
					.build();
		}
		return response;
	}

}
