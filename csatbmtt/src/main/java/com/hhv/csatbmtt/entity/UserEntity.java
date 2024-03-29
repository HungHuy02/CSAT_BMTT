package com.hhv.csatbmtt.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	private String citizenIdentificationNumber;
	
	@OneToMany(mappedBy = "entityMain")
	private List<PermissionEntity> listMain;
	
	@OneToMany(mappedBy = "entityOther")
	private List<PermissionEntity> listOther;

//	@Column(name = "created_by")
//	@CreatedBy
//	private String createdBy;
//
//	@Column(name = "modified_by")
//	@LastModifiedBy
//	private String modifiedBy;
//
//	@Column(name = "created_date")
//	@CreatedDate
//	private Date createdDate;
//
//	@Column(name = "modified_date")
//	@LastModifiedDate
//	private Date modifiedDate;

	@Column
	private String password;
	
	@Column
	private String name;

	@Column
	private String birthday;

	@Column
	private String email;
	
	@Column
	private String atm;

	@Column
	private String phoneNumber;

	@Column(columnDefinition = "TEXT")
	private String address;
	
	@Column(columnDefinition = "TEXT")
	private String publicKey;
	
	@Column(columnDefinition = "TEXT")
	private String privateKey;
}
