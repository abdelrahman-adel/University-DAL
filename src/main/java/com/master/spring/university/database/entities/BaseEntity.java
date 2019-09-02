package com.master.spring.university.database.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseEntity {

	@Override
	public String toString() {
		String stringRepresentation = this.getClass().getSimpleName() + " [";
		try {
			Method[] getters = this.getClass().getDeclaredMethods();
			for (Method getter : getters) {
				if (getter.getName().startsWith("get")) {
					stringRepresentation += getter.getName() + "=" + getter.invoke(this, (Object[]) null) + ", ";
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		int removeIndex;
		if ((removeIndex = stringRepresentation.lastIndexOf(", ")) != -1) {
			stringRepresentation = stringRepresentation.substring(0, removeIndex);
		}
		stringRepresentation += "]";
		return stringRepresentation;
	}
}
