package com.mydomain.api.model;

import com.google.common.base.Objects;
import java.io.Serializable;

public class Link implements Serializable {

	private String[] rel;
	private String href;
	private String title;

  /**
   * A navigational link that can be specified for an entity in Siren.
   * @param rel the relationship of this link to the entity
   * @param href the URL of the linked resource
   * @param title an optional text describing the nature of the link
   */
	public Link(String[] rel, String href, String title)  {
		this.rel = rel;
		this.href = href;
		this.title = title;
	}

	public Link(String[] rel, String href)  {
		this.rel = rel;
		this.href = href;
	}

    public String[] rel()
    {
        return rel;
    }

    public String href()
    {
        return href;
    }

    public String title()
    {
        return title;
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).toString();
	}

}
