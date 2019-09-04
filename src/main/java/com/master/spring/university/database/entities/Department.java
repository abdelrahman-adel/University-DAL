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
import javax.persistence.OneToOne;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Department.findByAttributes", query = "select d from Department d where (:ignoreId=true or d.id=:id)"
				+ " and (:ignoreName=true or d.name=:name)") })
public class Department extends BaseEntity {

	public Department(String name, College college, Professor professor) {
		super();
		this.name = name;
		this.college = college;
		this.professor = professor;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COLLEGE_ID", nullable = false)
	private College college;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROFESSOR_ID", nullable = false)
	private Professor professor;

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

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
