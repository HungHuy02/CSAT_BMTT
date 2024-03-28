package com.hhv.csatbmtt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhv.csatbmtt.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	
//	UserEntity findByIdAndPassword(String id, String password)

	boolean existsByCitizenIdentificationNumber(String citizenIdentificationNumber);
	boolean existsByAtm(String atm);
	boolean existsByPhoneNumber(String phone);
	boolean existsByEmail(String email);
	Optional<UserEntity> findByCitizenIdentificationNumber(String citizenIdentificationNumber);
}
