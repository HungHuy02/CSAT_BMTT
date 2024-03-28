package com.hhv.csatbmtt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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
		entity.setPhoneNumber(aes.encrypt(entity.getPhoneNumber(), iv));
		entity.setPassword(aes.encrypt(entity.getPassword(), iv));
		return entity;
	}
	
	public UserDTO decryptAll(UserEntity entity, UserDTO dto) {
		String iv = dto.getPassword();
		dto.setName(aes.decrypt(entity.getName(), iv));
		dto.setBirthday(aes.decrypt(entity.getBirthday(), iv));
		dto.setAddress(aes.decrypt(entity.getAddress(), iv));
		dto.setEmail(aes.decrypt(entity.getEmail(), iv));
		dto.setAtm(aes.decrypt(entity.getAtm(), iv));
		dto.setPhoneNumber(aes.decrypt(entity.getPhoneNumber(), iv));
		dto.setPassword(aes.decrypt(entity.getPassword(), iv));
		return dto;
	}
}
