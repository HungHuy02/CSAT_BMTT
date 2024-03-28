package com.hhv.csatbmtt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhv.csatbmtt.dto.PermissionDTO;
import com.hhv.csatbmtt.dto.UserDTO;
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
	public PermissionDTO findAllByIdMain(String idMain) {
		PermissionDTO response;
		List<PermissionEntity> entities = repository.findByEntityMain_citizenIdentificationNumber(idMain);
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
	public PermissionDTO delete(PermissionDTO dto) {
		PermissionDTO response;
		repository.deleteByEntityMain_citizenIdentificationNumberAndEntityOther_citizenIdentificationNumber(dto.getId_main(), dto.getId_others());
		List<PermissionEntity> list = repository.findByEntityMain_citizenIdentificationNumberAndEntityOther_citizenIdentificationNumber(dto.getId_main(), dto.getId_others());
		if(list.isEmpty()) {
			response = PermissionDTO.builder()
					.mes("Thành công")
					.success(true)
					.build();
		}else {
			response = PermissionDTO.builder()
					.mes("Thất bại")
					.success(false)
					.build();
		}
		return response;
	}

	@Override
	public PermissionDTO save(PermissionDTO dto) {
		dto.setEntityMain(UserDTO.builder().citizenIdentificationNumber(dto.getId_main()).build());
		dto.setEntityOther(UserDTO.builder().citizenIdentificationNumber(dto.getId_others()).build());
		PermissionDTO response;
		PermissionEntity entity = repository.save(mapper.map(dto, PermissionEntity.class));
		if(entity.getId() != null) {
			response = PermissionDTO.builder()
					.mes("Thành công")
					.success(true)
					.build();
		}else {
			response = PermissionDTO.builder()
					.mes("Thất bại")
					.success(false)
					.build();
		}
		return response;
	}

	@Override
	public PermissionDTO update(PermissionDTO dto) {
		PermissionDTO response;
		repository.deleteByEntityMain_citizenIdentificationNumberAndEntityOther_citizenIdentificationNumber(dto.getId_main(), dto.getId_others());
		List<PermissionEntity> list = repository.findByEntityMain_citizenIdentificationNumberAndEntityOther_citizenIdentificationNumber(dto.getId_main(), dto.getId_others());
		if(list.isEmpty()) {
			List<Long> ids = new ArrayList<>();
			for (PermissionDTO object : dto.getDataChange()) {
				PermissionEntity entity = repository.save(mapper.map(object, PermissionEntity.class));
				ids.add(entity.getId());
			}
			if(ids.size() == dto.getDataChange().size()) {
				response = PermissionDTO.builder()
						.mes("Thành công")
						.success(true)
						.build();
			}else {
				response = PermissionDTO.builder()
						.mes("Thất bại")
						.success(false)
						.build();
			}
		}else {
			response = PermissionDTO.builder()
					.mes("Thất bại")
					.success(false)
					.build();
		}
		
		return response;
	}

}
