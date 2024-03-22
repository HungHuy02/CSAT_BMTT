package com.hhv.csatbmtt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hhv.csatbmtt.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDTO {
	

	private Long id;
	private UserEntity entityMain;
	private String columnName;
	private UserEntity entityOther;
}
