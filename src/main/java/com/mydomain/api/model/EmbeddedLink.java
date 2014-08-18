package com.mydomain.api.model;

import java.util.ArrayList;

import com.google.common.base.Objects;

public class EmbeddedLink  extends Entity{

    /**
     * A sub entity that is only an embedded link, not a a full representation of the
     * sub entity.
     * @param rel the relationship between the parent entity  and this sub entity
     * @param href the URL of the linked sub entity
     * @param classes the optional classes of this entity
     */
	
	public ArrayList<String> rels = new ArrayList<String>();
	public String href = null;
	
	
	public ArrayList<String> rels()
    {
       return rels;
    }

	public void rels(ArrayList<String> rels)
    {
       this.rels = rels;
    }

	public String href()
    {
       return href;
    }

	public void href(String href)
    {
       this.href = href;
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).toString();
	}
}
