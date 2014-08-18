package com.mydomain.api.model;

/**
 * Sum type that encompasses all the supported encodings for actions in Siren.
 */
/** Companion object for the [[Encoding]] trait. */
public enum Encoding {
	
	
	/** The application/json encoding for an action's payload. */
	json ("application/json"),
	
	/** The application/x-www-form-urlencoded encoding for an action's payload. */
	formenconded ("application/x-www-form-urlencoded");

    private final String name;

    private Encoding(String s) {
        name = s;
    }

    public boolean equalsName(String mediaType){
        return (mediaType == null)? false:name.equals(mediaType);
    }

    public String toString(){
       return name;
    }
}
