package com.hhv.csatbmtt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhv.csatbmtt.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>{

	List<PermissionEntity> findByEntityMain_id(String idMain);
	List<PermissionEntity> findByEntityMain_idAndEntityOther_id(String id_main, String id_other);
	void deleteByEntityMain_idAndEntityOther_id(String id_main, String id_other);
}
