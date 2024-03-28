package com.hhv.csatbmtt.service;

import com.hhv.csatbmtt.dto.PermissionDTO;

public interface PermissionService {
	
	PermissionDTO findAll();
	PermissionDTO findAllByIdMain(String idMain);
	PermissionDTO delete(PermissionDTO dto);
	PermissionDTO save(PermissionDTO dto);
	PermissionDTO update(PermissionDTO dto);
}
