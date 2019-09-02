package com.master.spring.university.database.utils;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtendedRepository<T, ID> extends JpaRepository<T, ID> {

	List<T> findByAttributes(Parameters parameters);
}
