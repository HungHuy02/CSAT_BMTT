package com.hhv.csatbmtt.service;

import java.util.List;

import com.hhv.csatbmtt.dto.PermissionDTO;

public interface PermissionService {
	
	PermissionDTO findAll();
	PermissionDTO findAllByIdMain(String idMain);
	PermissionDTO delete(PermissionDTO dto);
	PermissionDTO save(List<PermissionDTO> dataChange, String token);
	PermissionDTO update(PermissionDTO dto, String token);
}
