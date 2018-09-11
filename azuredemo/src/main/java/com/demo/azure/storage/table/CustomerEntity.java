package com.demo.azure.storage.table;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class CustomerEntity extends TableServiceEntity {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public CustomerEntity(String lastName, String firstName) {
		
		this.firstName = lastName;
        this.lastName = firstName;
        
        this.partitionKey = lastName;
        this.rowKey = firstName;
    }
	
	public CustomerEntity() {
   
    }
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "CustomerEntity [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

	
	

}