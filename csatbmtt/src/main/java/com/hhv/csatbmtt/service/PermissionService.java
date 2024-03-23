package com.hhv.csatbmtt.service;

import com.hhv.csatbmtt.dto.PermissionDTO;

public interface PermissionService {
	
	PermissionDTO findAll();
	PermissionDTO findByIdMain(String idMain);
}
