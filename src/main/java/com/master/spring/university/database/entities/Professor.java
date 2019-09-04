package com.master.spring.university.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Professor.findByAttributes", query = "select p from Professor p where (:ignoreId=true or p.id=:id)"
				+ " and (:ignoreName=true or p.name=:name)"
				+ " and (:ignoreMobile=true or p.mobile=:mobile)"
				+ " and (:ignoreAddress=true or p.address=:address)") })
public class Professor extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private int id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "MOBILE", nullable = false)
	private String mobile;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

}
