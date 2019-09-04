package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Specialty.findByAttributes", query = "select s from Specialty s where (:ignoreId=true or s.id=:id) and (:ignoreName=true or s.name=:name)") })
public class Specialty extends BaseEntity {

	public Specialty(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false)
	private Department department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
