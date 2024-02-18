package com.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Customer {

	@Id
	private String acc_No;
	
	private Name cust_Name;
	
	private Address addrs;
	
	private String aadhar_No;
	
	private String pan_No;
	
	private String birth_Date;
	
	private String mo_NO;
	
	private double amount;
	
	private String pin;
	
	private String act_Type;
	
	
	
	private String pass;
}
