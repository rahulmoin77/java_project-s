package com.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Staff {

	@Id
	private String s_Id;
	
	private Name s_name;
	
	private Address addrs;
	
	private String aadhar_No;
	
	private String  pan_No;
	
	private String birth_Date;
	
	private String mo_No;
	
	private String pass;
	
	
}
