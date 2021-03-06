package com.mydomain.model;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.mydomain.api.model.Link;

public class Employee {

	@Expose private String name;
	
	@Expose (serialize = false) private String emailAddress;
	
	@Expose (serialize = true, deserialize = false) private Department department;
	
	@Expose private Link link;

	private Integer yearJoined;

	public Employee(String name, String emailAddress, Department department,
			Integer yearJoined) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.department = department;
		this.yearJoined = yearJoined;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Department getDepartment() {
		return department;
	}

	public Integer getYearJoined() {
		return yearJoined;
	}
	
	public void setLink(Link link){
		this.link = link;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).add("name", name)
				.add("emailAddress", emailAddress)
				.add("department", department).add("yearJoined", yearJoined)
				.toString();
	}
}
