package com.mydomain.api.model;

import java.util.ArrayList;

import com.google.common.base.Objects;


/**
 * An embedded entity, i.e. a sub entity of a [[Entity.RootEntity]]
 */
public class EmbeddedEntity extends Entity {

	 /** the relationship between the parent entity  and this sub entity
	  *  def rel: Rels
	  *  */
	public ArrayList<String> rels = new ArrayList<String>();
	
	public ArrayList<String> rels()
    {
        return rels;
    }

	public void rels(ArrayList<String> rels)
    {
        this.rels = rels;
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).toString();
	}
}
