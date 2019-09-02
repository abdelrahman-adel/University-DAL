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
		@NamedQuery(name = "PlannedCourse.findByAttributes", query = "select pc from PlannedCourse pc where pc.id=:id") })
public class PlannedCourse extends BaseEntity {

	@Id
	@GeneratedValue
	@JoinColumn(name = "ID", nullable = false)
	private int id;

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

}
