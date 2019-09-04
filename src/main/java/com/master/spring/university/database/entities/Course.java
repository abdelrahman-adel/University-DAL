package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "CREDIT_HOURS", nullable = false)
	private Integer creditHours;

}
