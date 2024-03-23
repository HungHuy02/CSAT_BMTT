package com.hhv.csatbmtt.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

	private String id;
//	private String createdBy;
//	private String modifiedBy;
//	private Date createdDate;
//	private Date modifiedDate;

	private String name;
	private String password;
	private Date birthday;
	private String email;
	private String atm;
	private String phone;
	private String address;
	
	private Boolean success;
	private String mes;
}
