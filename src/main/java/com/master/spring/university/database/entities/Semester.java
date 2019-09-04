package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Semester.findByAttributes", query = "select s from Semester s where (:ignoreId=true or s.id=:id) and (:ignoreValue=true or s.value=:value)") })
public class Semester extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "VALUE", nullable = false)
	private SemesterEnum value;

}
