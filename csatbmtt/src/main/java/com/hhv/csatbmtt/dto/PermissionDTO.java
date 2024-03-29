package com.hhv.csatbmtt.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDTO {
	

	private Long id;
	private UserDTO entityMain;
	private String columnName;
	private UserDTO entityOther;
	
	private String id_main;
	private String id_others;
	
	private Boolean success;
	private String mes;
	
	private List<PermissionDTO> dataChange;
	private List<PermissionDTO> dtos;
	
	private List<PermissionDTO> listDataUserAuthorizations;

}
