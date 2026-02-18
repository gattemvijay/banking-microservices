package com.mybank.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "EMAIL",unique = true)
	    private String email;
	    
	    @Column(name = "USER_NAME")
	    private String userName;
	    
	    @Column(name = "PASSWORD")
	    private String password;
	    
	    @Column(name = "PHONE")
	    private String phone;
	    

}
