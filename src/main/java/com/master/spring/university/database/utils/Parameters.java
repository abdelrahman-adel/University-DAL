package com.master.spring.university.database.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.master.spring.university.database.entities.BaseEntity;

public class Parameters {

	private static final String IGNORE_PREFIX = "ignore";

	private Map<String, Object> parametersMap;

	public Parameters() {
		parametersMap = new ConcurrentHashMap<>();
	}

	public Parameters addParameter(String key, Object value) {
		if (null == value) {
			return this;
		} else {
			parametersMap.put(key, value);
		}
		return this;
	}

	@Deprecated
	public Parameters addParameterOLD(String key, Object value) {
		String ignorKey = IGNORE_PREFIX + ("" + key.charAt(0)).toUpperCase() + key.substring(1);
		if (null == value) {
			parametersMap.put(ignorKey, true);
			parametersMap.put(key, value);
		} else {
			if (value instanceof BaseEntity) {
				Method[] getters = value.getClass().getMethods();
				for (Method getter : getters) {
					if (getter.getName().startsWith("get") && !getter.getName().equals("getClass")) {
						String keyExtension = getter.getName().substring(3);
						parametersMap.put(ignorKey + keyExtension, false);
						try {
							parametersMap.put(key + keyExtension, getter.invoke(value, (Object[]) null));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				parametersMap.put(ignorKey, false);
				parametersMap.put(key, value);
			}

		}
		return this;
	}

	public Map<String, Object> getParametersMap() {
		return parametersMap;
	}
}
