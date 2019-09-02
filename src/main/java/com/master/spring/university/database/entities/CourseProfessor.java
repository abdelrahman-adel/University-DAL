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
		@NamedQuery(name = "CourseProfessor.findByAttributes", query = "select cp from CourseProfessor cp where cp.id=:id") })
public class CourseProfessor extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PLANNED_COURSE_ID", nullable = false)
	private PlannedCourse plannedCourse;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROFESSOR_ID", nullable = false)
	private Professor professor;

}
