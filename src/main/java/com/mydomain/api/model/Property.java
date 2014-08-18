package com.mydomain.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Companion object for the property type.
 */
public class Property extends Object{

	/** Sum type for property values
	 * sealed trait Value
	 *  */

    public Object properties;

	public Property() {
		super();
	}

	public Property(Object properties) {
		super();
		this.properties = properties;
	}

	public Object properties()
    {
       return properties;
    }

	public void properties(Object value)
    {
       this.properties = value;
    }

	/**
	 * A property value that is string-typed.
	 * @param value the string value
	 * case class StringValue(value: String) extends Value
	 */
	public void value(String value){
		this.properties = value;
	}

    /**
     * A property value that is number-typed.
     * @param value the number value
     * case class NumberValue(value: BigDecimal) extends Value
     */
	public void value(BigDecimal value){
		this.properties = value;
	}

    /**
     * A property value that is boolean-typed.
     * @param value the boolean value
     * case class BooleanValue(value: Boolean) extends Value
     */
	public void value(Boolean value){
		this.properties = value;
	}
 
    
}
