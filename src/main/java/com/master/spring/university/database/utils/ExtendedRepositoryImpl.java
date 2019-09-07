package com.master.spring.university.database.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.master.spring.university.database.entities.BaseEntity;

public class ExtendedRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements ExtendedRepository<T, ID> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	EntityManager entityManager;

	public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

//	@Override
	public List<T> findByAttributes2(Parameters parameters) {
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

	@Override
	public List<T> findByAttributes(Parameters parameters) {
		if (null == parameters || parameters.getParametersMap().isEmpty()) {
			logger.warn("findByAttributes(null or empty parameters)");
			return null;
		}

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(this.getDomainClass());
		Root<T> root = query.from(this.getDomainClass());
		logger.info("findByAttributes @@ root is: {}", root);
		Map<String, Root<T>> prepareEntitiesRoots = prepareEntitiesRoots(query, parameters);
		prepareEntitiesRoots.put("", root);
		logger.info("findByAttributes @@ root is: {}", root);
		Predicate[] predicates = preparePredicates(builder, query, root, parameters);
		query.select(root).where(predicates);

		TypedQuery<T> findByAttributes = entityManager.createQuery(query);
		return findByAttributes.getResultList();
	}

	@SuppressWarnings("unchecked")
	private Map<String, Root<T>> prepareEntitiesRoots(CriteriaQuery<T> query, Parameters parameters) {
		Map<String, Root<T>> roots = new HashMap<>();
		parameters.getParametersMap().forEach((key, value) -> {
			if (value instanceof BaseEntity) {
				roots.put(key, (Root<T>) query.from(value.getClass()));
			}
		});
		return roots;
	}

	private Predicate[] preparePredicates(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> entity,
			Parameters parameters) {
		List<Predicate> predicates = new ArrayList<>();
		parameters.getParametersMap().forEach((key, value) -> {
			logger.info("preparePredicates @@ AT Key: {}, root is:{}", key, entity);
			if (value instanceof BaseEntity) {
				Method[] getters = value.getClass().getMethods();
				for (Method getter : getters) {
					if (getter.getName().startsWith("get") && !getter.getName().equals("getClass")) {
						try {
							Object fieldVal = getter.invoke(value, (Object[]) null);
							String fieldName = getter.getName().substring(3).toLowerCase();
							String keyExt = key + "." + fieldName;
							parameters.addParameter(keyExt, fieldVal);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
				logger.info("preparePredicates @@ root is:{}", entity);
				logger.info("preparePredicates @@ Parameters:{}", parameters.getParametersMap());
			} else {
				predicates.add(builder.equal(entity.get(key), value));
			}
		});
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
