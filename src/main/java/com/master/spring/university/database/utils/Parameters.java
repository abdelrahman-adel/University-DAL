package com.master.spring.university.database.utils;

import java.util.HashMap;
import java.util.Map;

public class Parameters {

	private static final String IGNORE_PREFIX = "ignore";

	private Map<String, Object> parametersMap;

	public Parameters() {
		parametersMap = new HashMap<>();
	}

	public Parameters addParameter(String key, Object value) {
		String ignorKey = IGNORE_PREFIX + ("" + key.charAt(0)).toUpperCase() + key.substring(1);
		if (null == value) {
			parametersMap.put(ignorKey, true);
		} else {
			parametersMap.put(ignorKey, false);
			parametersMap.put(key, value);
		}
		return this;
	}

	public Map<String, Object> getParametersMap() {
		return parametersMap;
	}
}
