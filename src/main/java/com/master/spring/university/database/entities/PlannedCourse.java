package com.master.spring.university.database.entities;

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
		@NamedQuery(name = "PlannedCourse.findByAttributes", query = "select pc from PlannedCourse pc where (:ignoreId=true or pc.id=:id)") })
public class PlannedCourse extends BaseEntity {

	public PlannedCourse(Course course, College college, Department department, Specialty specialty) {
		super();
		this.course = course;
		this.college = college;
		this.department = department;
		this.specialty = specialty;
	}

	@Id
	@GeneratedValue
	@JoinColumn(name = "ID", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURSE_ID", nullable = false)
	private Course course;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COLLEGE_ID", nullable = false)
	private College college;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = true)
	private Department department;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SPECIALTY_ID", nullable = true)
	private Specialty specialty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

}
