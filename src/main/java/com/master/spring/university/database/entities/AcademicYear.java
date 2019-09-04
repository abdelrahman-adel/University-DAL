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
		@NamedQuery(name = "AcademicYear.findByAttributes", query = "select a from AcademicYear a where (:ignoreId=true or a.id=:id)") })
public class AcademicYear extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLANNED_COURSE_ID", nullable = false)
	private PlannedCourse plannedCourse;

	@Column(name = "ACADEMIC_YEAR", nullable = false)
	private String academicYear;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEMESTER_ID", nullable = false)
	private Semester semester;

}
