/**
 * 
 */
package com.mydomain.api.model;

/**
 * A sum type for all possible types of a field.
 */

/**
 * Companion object for the [[Type]] trait.
 */

public enum Type {
	hidden,
	text,
	search,
	tel,
	url,
	email,
	password,
	datetime,
	date,
	month,
	week,
	time,
	datetime_local,
	number,
	range,
	color,
	checkbox,
	radio,
	file,
	image,
	reset,
	button
}
