package com.mydomain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Test;

import com.mydomain.api.model.*;
import com.mydomain.model.Department;
import com.mydomain.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {


	
	private Gson gson = new Gson();
	private Gson gsonSerializingNulls = new GsonBuilder().serializeNulls().create();

	@Test
	public void object2SirenLink() {
		// new link 
		String[] rel = {"self"};
		Link l = new Link(rel, "http://api.mydomain.com/v1/weather","Weather API basepath");
		String jsonString = gson.toJson(l);

		System.out.println(jsonString);
		
		// then
		assertThat(
				jsonString,
				is("{\"rel\":[\"self\"],\"href\":\"http://api.mydomain.com/v1/weather\",\"title\":\"Weather API basepath\"}"));
	}

	@Test
	public void object2SirenProperty() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",
				Department.DEVELOPMENT, 2012);
		
		Property p = new Property();
		p.properties(e);
		
		String jsonString = gson.toJson(p);

		System.out.println(jsonString);
		
		// then
		assertThat(
				jsonString,
				is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"emailAddress\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}}"));
	}

	@Test
	public void object2SirenAction() {
		// new action 
		String[] rel = {"self"};
		String name = "update-weather";
		String title = "Update your Weather";
		Method method = Method.PUT;
		String href = "http://api.mydomain.com/v1/weather";
		String type = "application/x-www-form-urlencoded";
		
		ArrayList<Field> fields = new ArrayList<Field>();
		
		Field f = new Field("name","title",Type.number,new Long(10));
		fields.add(f);
		
		Action a = new Action(name,title, method, href, type,fields);
		String jsonString = gson.toJson(a);

		System.out.println(jsonString);
		
		// then
		assertThat(
				jsonString,
				is("{\"name\":\"update-weather\",\"title\":\"Update your Weather\",\"method\":\"PUT\",\"href\":\"http://api.mydomain.com/v1/weather\",\"type\":\"application/x-www-form-urlencoded\",\"fields\":[{\"name\":\"name\",\"title\":\"title\",\"type\":\"number\",\"value\":10}]}"));
	}


	@Test
	public void object2SirenBasicEntity() {
		// new root entity 
		ArrayList<String> classes = new ArrayList<String>();
		classes.add("myclass-name");
		
		Entity e = new Entity(classes);
		
		
		String jsonString = gson.toJson(e);
		
		System.out.println(jsonString);
		
		// then
		assertThat(jsonString,is("{\"classes\":[\"myclass-name\"]}"));
	}

	@Test
	public void object2SirenEmbeddedEntity() {
		// new root entity 
		ArrayList<String> classes = new ArrayList<String>();
		classes.add("myclass-name");
		
		ArrayList<String> rels = new ArrayList<String>();
		rels.add("self");
		
		EmbeddedEntity e = new EmbeddedEntity();
		e.classes(classes);
		e.rels(rels);
		
		String jsonString = gson.toJson(e);
		
		System.out.println(jsonString);
		
		// then
		assertThat(jsonString,is("{\"rels\":[\"self\"],\"classes\":[\"myclass-name\"]}"));
	}

	@Test
	public void object2SirenEmbeddedLink() {
		// new root entity 
		ArrayList<String> classes = new ArrayList<String>();
		classes.add("items");
		classes.add("collection");
		
		ArrayList<String> rels = new ArrayList<String>();
		rels.add("http://x.io/rels/order-items");
		
		EmbeddedLink e = new EmbeddedLink();
		e.classes(classes);
		e.rels(rels);
		e.href("http://api.x.io/orders/42/items");
		
		String jsonString = gson.toJson(e);
		
		System.out.println(jsonString);
		
		// then
		assertThat(jsonString,is("{\"rels\":[\"http://x.io/rels/order-items\"],\"href\":\"http://api.x.io/orders/42/items\",\"classes\":[\"items\",\"collection\"]}"));
	}

	@Test
	public void object2SirenEntityRepresentation() {
		// new root entity 
		ArrayList<String> classes = new ArrayList<String>();
		classes.add("employee");

		Employee emp = new Employee("Soo Philip Kim", "philipjkim@gmail.com",
				Department.DEVELOPMENT, 2012);
		
		
		ArrayList<Action> actions = new ArrayList<Action>();
		Action action = new Action();
		
		// new action 
		action.name = "add-employee";
		action.title = "Add New Employee";
		action.method = Method.PUT;
		action.href = "http://api.mydomain.com/v1/employee";
		action.type = "application/x-www-form-urlencoded";

		ArrayList<Field> fields = new ArrayList<Field>();
		Field f = new Field("name","title",Type.number,new Long(10));
		fields.add(f);
		action.fields(fields);
		actions.add(action);
				
		ArrayList<Link> links = new ArrayList<Link>();
		String[] rel = {"self"};
		Link l = new Link(rel, "http://api.mydomain.com/v1/weather","Weather API basepath");
		links.add(l);
		
		
		
		EntityRepresentation e = new EntityRepresentation();
		e.properties(emp);
		e.classes(classes);
		e.actions(actions);
		e.links(links);
		
		String jsonString = gson.toJson(e);
		
		System.out.println(jsonString);
		
		// then
		assertThat(true,is(true));
	}

	@Test
	public void object2Json() {
		// given
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",
				Department.DEVELOPMENT, 2012);

		// when
		String jsonString = gson.toJson(e);

		// then
		assertThat(
				jsonString,
				is("{\"name\":\"Soo Philip Kim\",\"emailAddress\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}"));
	}

	@Test
	public void object2JsonUsingDefaultGsonWhenNullFieldsExist() {
		// given
		Employee e = new Employee(null, null, Department.DEVELOPMENT, 2012);

		// when
		String jsonString = gson.toJson(e);

		// then
		assertThat(jsonString,
				is("{\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}"));
	}

	@Test
	public void object2JsonUsingNullSerializingGsonWhenNullFieldsExist() {
		// given
		Employee e = new Employee(null, null, Department.DEVELOPMENT, 2012);

		// when
		String jsonString = gsonSerializingNulls.toJson(e);

		// then
		assertThat(
				jsonString,
				is("{\"name\":null,\"emailAddress\":null,\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}"));
	}

	@Test
	public void json2Object() {
		// given
		String jsonString = "{\"name\":\"Soo Philip Kim\",\"emailAddress\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}";

		// when
		Employee e = gson.fromJson(jsonString, Employee.class);

		// then
		assertThat(e.getName(), is("Soo Philip Kim"));
		assertThat(e.getEmailAddress(), is("philipjkim@gmail.com"));
		assertThat(e.getDepartment(), is(Department.DEVELOPMENT));
		assertThat(e.getYearJoined(), is(2012));
	}

	@Test
	public void json2ObjectWithNullsUsingDefaultGson() {
		// given
		String jsonString = "{\"name\":null,\"emailAddress\":null,\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}";

		// when
		Employee e = gson.fromJson(jsonString, Employee.class);

		// then
		assertThat(e.getName(), is(nullValue()));
		assertThat(e.getEmailAddress(), is(nullValue()));
		assertThat(e.getDepartment(), is(Department.DEVELOPMENT));
		assertThat(e.getYearJoined(), is(2012));
	}

	@Test
	public void json2ObjectWithNullsUsingNullSerializingGson() {
		// given
		String jsonString = "{\"name\":null,\"emailAddress\":null,\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}";

		// when
		Employee e = gsonSerializingNulls.fromJson(jsonString, Employee.class);

		// then
		assertThat(e.getName(), is(nullValue()));
		assertThat(e.getEmailAddress(), is(nullValue()));
		assertThat(e.getDepartment(), is(Department.DEVELOPMENT));
		assertThat(e.getYearJoined(), is(2012));
	}
}
