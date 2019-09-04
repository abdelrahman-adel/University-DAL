package com.master.spring.university.database.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Student.findByAttributes", query = "select s from Student s where (:ignoreId=true or s.id=:id)"
				+ " and (:ignoreName=true or s.name=:name)"
				+ " and (:ignoreMobile=true or s.mobile=:mobile)"
				+ " and (:ignoreAddress=true or s.address=:address)"
				+ " and (:ignoreJoiningDate=true or s.joiningDate=:joiningDate)") })
public class Student extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "MOBILE", nullable = false)
	private String mobile;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "JOINING_DATE", nullable = false)
	private Date joiningDate;

	public Student() {
	}

	public Student(String name, String mobile, String address, Date joiningDate) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.joiningDate = joiningDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

//	@Override
//	public String toString() {
//		return "Student [id=" + id + ", name=" + name + ", mobile=" + mobile + ", address=" + address + ", joiningDate="
//				+ joiningDate + "]";
//	}
}
