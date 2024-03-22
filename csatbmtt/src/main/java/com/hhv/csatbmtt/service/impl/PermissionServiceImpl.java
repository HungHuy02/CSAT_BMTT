package com.hhv.csatbmtt.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhv.csatbmtt.dto.PermissionDTO;
import com.hhv.csatbmtt.repository.PermissionRepository;
import com.hhv.csatbmtt.service.PermissionService;

public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<PermissionDTO> findAll() {
		return repository.findAll().stream()
				.map(entity -> mapper.map(entity, PermissionDTO.class))
				.collect(Collectors.toList());
	}

}
