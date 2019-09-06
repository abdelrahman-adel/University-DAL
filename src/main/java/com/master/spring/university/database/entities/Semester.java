package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Semester.findByAttributes", query = "select s from Semester s where (:ignoreId=true or s.id=:id) and (:ignoreValue=true or s.value=:value)") })
public class Semester extends BaseEntity {

	public Semester(SemesterEnum value) {
		super();
		this.value = value;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEMESTER_SEQ")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "VALUE", nullable = false)
	private SemesterEnum value;

	public Semester() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SemesterEnum getValue() {
		return value;
	}

	public void setValue(SemesterEnum value) {
		this.value = value;
	}

}
