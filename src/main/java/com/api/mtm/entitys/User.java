package com.api.mtm.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user")
@Getter @Setter @ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="user_name")
	String userName;

	@Column(name="user_contact")
	String userContact;

	@Column(name="user_email")
	String userEmail;

	@Column(name="user_addr")
	String userAddress;

	@Column(name="user_salary")
	float userSalary;

	@Column(name="user_age")
	int userAge;

	@Column(name="user_gender")
	String userGender;
	
	@Column(name="created_dt_time")
	Date createdDtTime;
}
