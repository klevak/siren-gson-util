package com.mydomain.api.model;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Action implements Serializable {

    public String name;
    public String title;
    public Method method;
    public String href;
    public String type;
    public ArrayList<Field> fields;

	public Action()  {
		super();
	}
	
    /**
     * An action that can be specified for an entity in Siren.
     * @param name the name of the action
     * @param href the URL to be used for executing the action
     * @param classes the optional classes of the action
     * @param title optional descriptive text about the action
     * @param method the HTTP method to be used when executing the action
     * @param `type` the encoding to be used for the payload when sending the request to the
     *               URL of this action
     * @param fields the fields specified for this action
     */
    
	public Action(String name, String title, Method method, 
			String href, String type, ArrayList<Field> fields)  {
		this.name = name;
		this.title = title;
		this.method = method;
        this.href = href;
        this.type = type;
        this.fields = fields;
	}

    public String name()
    {
        return name;
    }

    public void name(String name)
    {
       this.name = name;
    }
    
    public String title()
    {
        return title;
    }

    public void title(String title)
    {
        this.title = title;
    }

    public Method method()
    {
        return method;
    }

    public void method(Method method)
    {
        this.method = method;
    }

    public String href()
    {
        return href;
    }

    public void href(String href)
    {
        this.href = href;
    }

    public String type()
    {
        return type;
    }

    public void type(String type)
    {
        this.type = type;
    }
 
    public ArrayList<Field> fields()
    {
        return fields;
    }

    public void fields(ArrayList<Field> fields)
    {
        this.fields = fields;
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this.getClass()).toString();
	}

}
