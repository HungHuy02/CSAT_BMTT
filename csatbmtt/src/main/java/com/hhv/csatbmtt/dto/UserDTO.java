package com.hhv.csatbmtt.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private String citizenIdentificationNumber;
//	private String createdBy;
//	private String modifiedBy;
//	private Date createdDate;
//	private Date modifiedDate;

	private String name;
	private String password;
	private String birthday;
	private String email;
	private String atm;
	private String phoneNumber;
	private String address;
	private String publicKey;
	private String privateKey;
	
	private String token;
	
	private UserDTO individual;

	private Boolean success;
	private String mes;
}
