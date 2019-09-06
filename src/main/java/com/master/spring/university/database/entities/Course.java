package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Course.findByAttributes", query = "select c from Course c where (:ignoreId=true or c.id=:id)"
				+ " and (:ignoreName=true or c.name=:name)"
				+ " and (:ignoreCreditHours=true or creditHours=:creditHours)") })
public class Course extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_SEQ")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "CREDIT_HOURS", nullable = false)
	private Integer creditHours;

	public Course() {
	}

	public Course(String name, Integer creditHours) {
		super();
		this.name = name;
		this.creditHours = creditHours;
	}

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

	public Integer getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(Integer creditHours) {
		this.creditHours = creditHours;
	}

}
