package org.api.pet;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.testng.Assert;
public class PetStore {

	
	public static void main(String[] args) {
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
	// Post the pet and get the pet id	
String response = given().log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				"  \"id\": 158,\r\n" + 
				"  \"category\": {\r\n" + 
				"    \"id\": 15,\r\n" + 
				"    \"name\": \"Jenny\"\r\n" + 
				"  },\r\n" + 
				"  \"name\": \"doggie\",\r\n" + 
				"  \"photoUrls\": [\r\n" + 
				"    \"string\"\r\n" + 
				"  ],\r\n" + 
				"  \"tags\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": 18,\r\n" + 
				"      \"name\": \"New Born\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"status\": \"available\"\r\n" + 
				"}")
		.when().post("/pet")
		.then().log().all().assertThat().statusCode(200).body("id",equalTo(158))
		.extract().response().asString();

  JsonPath js = new JsonPath(response);
  System.out.println(response);
  
  int id=js.get("id");
  System.out.println("The Common id is" + id);
  int id1=js.get("category.id");
  System.out.println("The Category is is " + id1);
  int id2=js.get("tags[0].id");
  System.out.println("The Tag id is " + id2);
  
  //get pet id 
  
  String getid= given().log().all().header("Content-Type","application/json")
  .pathParam("id", id).when().get("/pet/{id}")
  .then().assertThat().statusCode(200).extract().response().asString();
  
  JsonPath js1=new JsonPath(getid);
  int newid=js1.get("id");
  System.out.println("The id is: "  + newid);
  Assert.assertEquals(id, newid);
  

  
  
	}
	
}
