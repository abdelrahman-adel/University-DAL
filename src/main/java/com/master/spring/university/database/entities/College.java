package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "College.findByAttributes", query = "select c from College c where (:ignoreId=true or c.id=:id)"
				+ " and (:ignoreName=true or c.name=:name)"
				+ " and (:ignoreDeputyId=true or c.deputy.id=:deputyId)"
				+ " and (:ignoreDeputyName=true or c.deputy.name=:deputyName)"
				+ " and (:ignoreDeputyMobile=true or c.deputy.mobile=:deputyMobile)"
				+ " and (:ignoreDeputyAddress=true or c.deputy.address=:deputyAddress)") })
public class College extends BaseEntity {

	public College(String name, Professor professor) {
		super();
		this.name = name;
		this.deputy = professor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLLEGE_SEQ")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPUTY_ID", nullable = false)
	private Professor deputy;

	public College() {
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

	public Professor getDeputy() {
		return deputy;
	}

	public void setDeputy(Professor professor) {
		this.deputy = professor;
	}

}
