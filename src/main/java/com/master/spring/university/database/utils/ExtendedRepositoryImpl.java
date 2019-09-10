package com.master.spring.university.database.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<T> findByAttributes(Parameters parameters) {
		if (null == parameters || parameters.isEmpty()) {
			logger.warn("findByAttributes(null or empty parameters)");
			return null;
		}

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(this.getDomainClass());
		Root<T> root = query.from(this.getDomainClass());
		root.alias(this.getDomainClass().getSimpleName().toLowerCase());

		prepareJoins(query, root, parameters);

		Predicate[] predicates = preparePredicates(builder, query, root, parameters);
		query.select(root).where(predicates);

		TypedQuery<T> findByAttributes = entityManager.createQuery(query);
		return findByAttributes.getResultList();
	}

	private void prepareJoins(CriteriaQuery<T> query, Root<T> root, Parameters parameters) {
		for (Pair pair : parameters) {
			if (pair.getValue() instanceof BaseEntity) {
				logger.info("prepareJoins @@ joining: {}", pair.getKey());
				root.join(pair.getKey()).alias(pair.getKey());
			}
		}
	}

	private Predicate[] preparePredicates(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root,
			Parameters parameters) {
		List<Predicate> predicates = new ArrayList<>();
		for (Pair pair : parameters) {
			logger.info("preparePredicates @@ BEGINNING parameters.size():{}", parameters.size());
			String key = pair.getKey();
			Object value = pair.getValue();
			logger.info("preparePredicates @@ AT Key: {}", key);
			if (value instanceof BaseEntity) {
				Method[] getters = value.getClass().getMethods();
				for (Method getter : getters) {
					if (getter.getName().startsWith("get") && !getter.getName().equals("getClass")) {
						try {
							Object fieldVal = getter.invoke(value, (Object[]) null);
							String fieldName = getter.getName().substring(3).toLowerCase();
							String keyExt = key + "." + fieldName;
							parameters.add(keyExt, fieldVal);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				logger.info("preparePredicates @@ Adding predicate: {} @ {}", key, value);
				if (key.contains(".")) {
					String joinName = key.substring(0, key.indexOf("."));
					String joinKey = key.substring(key.indexOf(".") + 1);
					root.getJoins().forEach((join) -> {
						if (joinName.equals(join.getAlias())) {
							predicates.add(builder.equal(join.get(joinKey), value));
						}
					});
				} else {
					predicates.add(builder.equal(root.get(key), value));
				}
			}
			logger.info("preparePredicates @@ ENDING parameters.size():{}", parameters.size());
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
