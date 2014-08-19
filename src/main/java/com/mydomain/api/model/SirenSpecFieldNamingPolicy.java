package com.mydomain.api.model;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;


/*
 * A mechanism for providing custom field naming in Gson. 
 * This allows the client code to translate field names 
 * into a particular convention that is not supported 
 * as a normal Java field declaration rules.
 */
public class SirenSpecFieldNamingPolicy implements FieldNamingStrategy{
	
	public String translateName(Field f) {
		return getConvention(f.getName());
	}
	
	private String getConvention(String name) {
		if(name.equals("emailAddress")) { return "email";}
		if(name.equals("classes")) { return "class";}
		return name;
	}

}
