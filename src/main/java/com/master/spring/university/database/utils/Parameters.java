package com.master.spring.university.database.utils;

import java.util.HashMap;
import java.util.Map;

public class Parameters {

	private Map<String, Object> parametersMap;

	public Parameters() {
		parametersMap = new HashMap<>();
	}

	public Parameters addParameter(String key, Object value) {
		parametersMap.put(key, value);
		return this;
	}

	public Map<String, Object> getParametersMap() {
		return parametersMap;
	}
}
