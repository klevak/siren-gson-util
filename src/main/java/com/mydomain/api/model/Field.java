package com.mydomain.api.model;

/**
 * Companion object for the [[Field]] type.
 */

import java.io.Serializable;

import com.google.common.base.Objects;



public class Field {

	public final String name;
	public final String title;
	public final Type type;
	private final Object value;

    /**
     * A field that specifies part of the payload for an action.
     * @param name the name of the field
     * @param `type` the type of the field
     * @param value the optional value of the field; only makes sense for certain types
     *              of fields;
     * @param title an optional textual annotation for the field
     */
	public Field(String name, String title, Type type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
		this.title = title;
	}

    public String name()
    {
        return name;
    }

    public String title()
    {
        return title;
    }
    
    public Type type()
    {
        return type;
    }

    public Object value()
    {
        return value;
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).toString();
	}

	
}
