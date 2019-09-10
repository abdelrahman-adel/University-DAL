package com.master.spring.university.database.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Parameters implements Iterator<Pair>, Iterable<Pair> {

	private int index;

	private Map<String, Object> parametersMap;
	private List<Pair> parameters;

	public Parameters() {
		index = 0;
		parametersMap = new ConcurrentHashMap<>();
		parameters = new ArrayList<>();
	}

	public Parameters add(String key, Object value) {
		if (null == value) {
			return this;
		} else {
			parametersMap.put(key, value);
			parameters.add(new Pair(key, value));
		}
		return this;
	}

	public Parameters addAll(List<Pair> pairs) {
		for (Pair pair : pairs) {
			if (null != pair && null != pair.getKey() && null != pair.getValue()) {
				parameters.add(pair);
			}
		}
		return this;
	}

	@Override
	public boolean hasNext() {
		if (parameters.size() > index)
			return true;
		return false;
	}

	@Override
	public Pair next() {
		if (hasNext())
			return parameters.get(index++);
		return null;
	}

	@Override
	public Iterator<Pair> iterator() {
		return this;
	}

	public int size() {
		return parameters.size();
	}

	public void resetIndex() {
		index = 0;
	}

	public List<Pair> getAll() {
		return parameters;
	}

	public boolean isEmpty() {
		if (size() > 0)
			return false;
		return true;
	}

	@Deprecated
	public Parameters addParameter(String key, Object value) {
		if (null == value)
			return this;
		else {
			parametersMap.put(key, value);
			parameters.add(new Pair(key, value));
		}
		return this;
	}

	@Deprecated
	public Map<String, Object> getParametersMap() {
		return parametersMap;
	}

	@Deprecated
	public List<Pair> getParameters() {
		return parameters;
	}
}

class Pair {
	private String key;
	private Object value;

	public Pair(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
