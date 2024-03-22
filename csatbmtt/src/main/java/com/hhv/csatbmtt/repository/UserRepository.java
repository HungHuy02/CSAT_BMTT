package com.hhv.csatbmtt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhv.csatbmtt.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	
//	UserEntity findByIdAndPassword(String id, String password)

	boolean existsById(String id);
	boolean existsByAtm(String atm);
	boolean existsByPhone(String phone);
	boolean existsByEmail(String email);
	Optional<UserEntity> findById(String id);
}
