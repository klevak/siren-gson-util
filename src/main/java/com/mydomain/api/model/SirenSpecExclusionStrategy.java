package com.mydomain.api.model;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;


public class SirenSpecExclusionStrategy implements ExclusionStrategy{
	
	public boolean shouldSkipClass(Class<?> c) {
		return false;
	}

	public boolean shouldSkipField(FieldAttributes f) {
		if(f.getName().equals("emailAddress")) {return true;}
		return false;
	}

}
