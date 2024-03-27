package com.hhv.csatbmtt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhv.csatbmtt.dto.UserDTO;
import com.hhv.csatbmtt.entity.UserEntity;
import com.hhv.csatbmtt.security.AES;

@Component
public class EncryptAndDecryptUtil {
	
	@Autowired
	private AES aes;
	
	public boolean checkPassword(UserEntity entity, UserDTO dto) {
		String decryptPassword = aes.decrypt(entity.getPassword(), dto.getPassword());
		return decryptPassword.equals(dto.getPassword());
	}
	
	public UserEntity encryptAll(UserEntity entity) {
		String iv = entity.getPassword();
		entity.setName(aes.encrypt(entity.getName(), iv));
		entity.setBirthday(aes.encrypt(entity.getBirthday(), iv));
		entity.setAddress(aes.encrypt(entity.getAddress(), iv));
		entity.setEmail(aes.encrypt(entity.getEmail(), iv));
		entity.setAtm(aes.encrypt(entity.getAtm(), iv));
		entity.setPhone(aes.encrypt(entity.getPhone(), iv));
		entity.setPassword(aes.encrypt(entity.getPassword(), iv));
		return entity;
	}
	
	public UserDTO decryptAll(UserDTO dto) {
		String iv = dto.getPassword();
		dto.setName(aes.encrypt(dto.getName(), iv));
		dto.setBirthday(aes.encrypt(dto.getBirthday(), iv));
		dto.setAddress(aes.encrypt(dto.getAddress(), iv));
		dto.setEmail(aes.encrypt(dto.getEmail(), iv));
		dto.setAtm(aes.encrypt(dto.getAtm(), iv));
		dto.setPhone(aes.encrypt(dto.getPhone(), iv));
		dto.setPassword(aes.encrypt(dto.getPassword(), iv));
		return dto;
	}
}
