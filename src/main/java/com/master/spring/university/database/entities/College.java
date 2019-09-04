package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "College.findByAttributes", query = "select c from College c where (:ignoreId=true or c.id=:id)"
				+ " and (:ignoreName=true or c.name=:name)") })
public class College extends BaseEntity {

	public College(String name, Professor professor) {
		super();
		this.name = name;
		this.professor = professor;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
