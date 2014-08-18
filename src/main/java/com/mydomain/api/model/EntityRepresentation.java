package com.mydomain.api.model;

import java.util.ArrayList;

import com.google.common.base.Objects;

/**
 * A fully represented entity.
 */
public class EntityRepresentation extends Entity {
	
	/** the optional properties of this entity 
	 * def properties: Option[Properties]
	 * */
	public Object properties = null;
	
    /** the optional actions specified for this entity 
     * def actions: Option[Actions]
     * */
	public ArrayList<Action> actions = new ArrayList<Action>();
	
    /** the optional links specified for this entity 
     * def links: Option[Links]
     * */
	public ArrayList<Link> links = new ArrayList<Link>();
	
    /** an optional descriptive text about this entity 
     * def title: Option[String]
     * */
	public String title = null;
	
	
	public EntityRepresentation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object properties()
    {
        return properties;
    }
	
	public void properties(Object properties)
    {
        this.properties = properties;
    }

	public ArrayList<Action> actions()
    {
        return actions;
    }

	public void actions(ArrayList<Action> actions)
    {
        this.actions = actions;
    }

	public ArrayList<Link> links()
    {
        return links;
    }

	public void links(ArrayList<Link> links)
    {
        this.links = links;
    }

	public String title()
    {
        return title;
    }

	public void title(String title)
    {
        this.title = title;
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).toString();
	}
	
}
