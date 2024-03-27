package com.hhv.csatbmtt.service;

import com.hhv.csatbmtt.dto.PermissionDTO;

public interface PermissionService {
	
	PermissionDTO findAll();
	PermissionDTO findByIdMain(String idMain);
	PermissionDTO delete(String id_main, String id_other);
	PermissionDTO save(PermissionDTO dto);
}
