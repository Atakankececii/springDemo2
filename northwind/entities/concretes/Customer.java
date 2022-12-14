package com.etiya.northwind.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "contact_name")
	private String contactName;

	@Column(name = "company_title")
	private String contactTitle;

	@Column(name = "address")
	private String address;

	

	@Column(name = "region")
	private String region;

	 @Column(name = "postal_code")
	 private String postalCode;


	
	 @Column(name = "phone") 
	 private String phone;
	  
	@Column(name = "fax") 
	private String fax;
	 
	
	
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@OneToMany(mappedBy = "customer")
	private List<Cart> cart;

}
