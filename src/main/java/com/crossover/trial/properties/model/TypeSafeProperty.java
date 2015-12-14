package com.crossover.trial.properties.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TypeSafeProperty implements Comparable<TypeSafeProperty> {

	private String name;

	private String value;

	private String type;

	public TypeSafeProperty(String name, String value, String type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name + ", " + type + ", " + value;
	}

	@Override
	public int compareTo(TypeSafeProperty o) {
		return name.compareToIgnoreCase(o.getName());
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
}
