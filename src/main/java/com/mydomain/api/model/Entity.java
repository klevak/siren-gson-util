package com.mydomain.api.model;

import java.util.ArrayList;

import com.google.common.base.Objects;

/**
 * A Siren entity.
 */
public class Entity {

	/** the optional classes of this entity 
	 * def classes: Option[Classes]
	 * */
	public ArrayList<String> classes = new ArrayList<String>();

	public Entity() {
		super();
	}

	public Entity(ArrayList<String> classes) {
		super();
		this.classes = classes;
	}
	
	public ArrayList<String> classes()
    {
        return classes;
    }
	
	public void classes(ArrayList<String> classes){
		this.classes = classes;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).toString();
	}
}
