package com.mydomain;

// stuff for testin
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Test;


// our Siren data model
import com.mydomain.api.model.*;
// our DTOs or Pojo
import com.mydomain.model.Department;
import com.mydomain.model.Employee;

import com.google.gson.FieldNamingPolicy;
// Google GSON
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonTest {


	
	private Gson gson = new Gson();

	// we are going to experient with a bunch of settings for google GSON
	private Gson gsonSerializingNulls = new GsonBuilder().serializeNulls().create();
	private Gson gsonDisableHtmlEscaping = new GsonBuilder().disableHtmlEscaping().create();
	private Gson gsonExcludeFieldsWithoutExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	private Gson gsonGenerateNonExecutableJson = new GsonBuilder().generateNonExecutableJson().create();
	private Gson gsonSerializeNulls = new GsonBuilder().serializeNulls().create();
	private Gson gsonSetFieldNamingPolicy = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
	private Gson gsonSetFieldNamingStrategy = new GsonBuilder().setFieldNamingStrategy(new SirenSpecFieldNamingPolicy()).create();
	private Gson gsonExclusionStrategy = new GsonBuilder().setExclusionStrategies(new SirenSpecExclusionStrategy()).create();

	@Test
	public void object2SirenPropertyExclusionStrategy() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",Department.DEVELOPMENT, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gsonExclusionStrategy.toJson(p);
		// then
		assertThat(
				jsonString,
				is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}}"));
	}

	@Test
	public void object2SirenPropertyFieldNamingStrategy() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",Department.DEVELOPMENT, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gsonSetFieldNamingStrategy.toJson(p);
		// then
		assertThat(
				jsonString,
				is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"email\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}}"));
	}

	@Test
	public void object2SirenPropertyLowerCaseNamingPolicy() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",Department.DEVELOPMENT, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gsonSetFieldNamingPolicy.toJson(p);
		// then
		assertThat(
				jsonString,
				is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"email_address\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"year_joined\":2012}}"));
	}

	
	@Test
	public void object2SirenSerializeNullsJsonProperty() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",null, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gsonSerializeNulls.toJson(p);
		// then
		assertThat(
				jsonString,
				is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"emailAddress\":\"philipjkim@gmail.com\",\"department\":null,\"yearJoined\":2012}}"));
	}
	
	@Test
	public void object2SirenSerializeNullsJsonPropertyNeg() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",null, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gson.toJson(p);
		// then
		assertThat(
				jsonString,
				is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"emailAddress\":\"philipjkim@gmail.com\",\"yearJoined\":2012}}"));
	}
	
	
	@Test
	public void object2SirenNonExecutableJsonProperty() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",Department.DEVELOPMENT, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gsonGenerateNonExecutableJson.toJson(p);
		// then
		assertThat(
				jsonString,
				is(")]}'\n{\"properties\":{\"name\":\"Soo Philip Kim\",\"emailAddress\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}}"));
	}
	
	
	@Test
	public void object2SirenHideNonExposedProperty() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",Department.DEVELOPMENT, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gsonExcludeFieldsWithoutExpose.toJson(p);
		// then
		assertThat(
				jsonString,
				is(""));
	}
	
	
	@Test
	public void object2SirenEscapedLink() {
		// new link 
		String[] rel = {"self"};
		Link l = new Link(rel, "http://api.mydomain.com/v1/weather?zipcode=04096","<title>page</title>");
		String jsonString = gson.toJson(l);
		
		// then.  Note that we need to escape the \\u00XX values
		assertThat(
				jsonString,
				is("{\"rel\":[\"self\"],\"href\":\"http://api.mydomain.com/v1/weather?zipcode\\u003d04096\",\"title\":\"\\u003ctitle\\u003epage\\u003c/title\\u003e\"}"));
	}
	
	@Test
	public void object2SirenLink() {
		// new link 
		String[] rel = {"self"};
		Link l = new Link(rel, "http://api.mydomain.com/v1/weather?zipcode=04096","<title>page</title>");
		String jsonString = gsonDisableHtmlEscaping.toJson(l);
		
		// then
		assertThat(
				jsonString,
				is("{\"rel\":[\"self\"],\"href\":\"http://api.mydomain.com/v1/weather?zipcode=04096\",\"title\":\"<title>page</title>\"}"));
	}

	@Test
	public void object2SirenProperty() {
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",Department.DEVELOPMENT, 2012);
		Property p = new Property();
		p.properties(e);
		String jsonString = gson.toJson(p);
		// then
		assertThat(
				jsonString,
				is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"emailAddress\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012}}"));
	}

	@Test
	public void object2SirenPropertyWithLink() {
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		
		Employee e = new Employee("Soo Philip Kim", "philipjkim@gmail.com",Department.DEVELOPMENT, 2012);
		String[] rel = {"self"};
		Link link = new Link(rel, "http://api.mydomain.com/v1/employee/" + e.getEmailAddress(),"<b>" + e.getName() + "</b>");
		e.setLink(link);
		
		employees.add(e);
		
		
		Property p = new Property();
		p.properties(employees);
		String jsonString = gson.toJson(p);
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
		Link l = new Link(rel, "http://api.mydomain.com/v1/employees","Employee API basepath");
		links.add(l);
		
		EntityRepresentation e = new EntityRepresentation();
		e.properties(emp);
		e.classes(classes);
		e.actions(actions);
		e.links(links);
		
		String jsonString = gsonSetFieldNamingStrategy.toJson(e);
		
		// then
		assertThat(jsonString,is("{\"properties\":{\"name\":\"Soo Philip Kim\",\"email\":\"philipjkim@gmail.com\",\"department\":\"DEVELOPMENT\",\"yearJoined\":2012},\"actions\":[{\"name\":\"add-employee\",\"title\":\"Add New Employee\",\"method\":\"PUT\",\"href\":\"http://api.mydomain.com/v1/employee\",\"type\":\"application/x-www-form-urlencoded\",\"fields\":[{\"name\":\"name\",\"title\":\"title\",\"type\":\"number\",\"value\":10}]}],\"links\":[{\"rel\":[\"self\"],\"href\":\"http://api.mydomain.com/v1/employees\",\"title\":\"Employee API basepath\"}],\"class\":[\"employee\"]}"));
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
