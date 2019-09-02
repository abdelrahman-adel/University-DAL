package com.master.spring.university.database.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class ExtendedRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements ExtendedRepository<T, ID> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	EntityManager entityManager;

	public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List<T> findByAttributes(Parameters parameters) {
		if (null == parameters || parameters.getParametersMap().isEmpty()) {
			logger.warn("findByAttributes(null or empty parameters)");
			return null;
		}

		String findByAttributesQueryName = this.getDomainClass().getSimpleName() + ".findByAttributes";
		TypedQuery<T> findByAttributes = entityManager.createNamedQuery(findByAttributesQueryName,
				this.getDomainClass());

		parameters.getParametersMap().forEach((key, value) -> findByAttributes.setParameter(key, value));
		return findByAttributes.getResultList();
	}

}
