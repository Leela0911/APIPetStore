package org.api.pet;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class UpdatePet {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		String response=given().log().all().header("Content-Type","application/json")
				.queryParam("status", "available")
				  .when().get("/pet/findByStatus")
				  .then().assertThat().statusCode(200).extract().response().asString();
				  JsonPath js2 = new JsonPath(response);
				  System.out.println("The Update pet Status : " +response);
				  String s=js2.get("status[1]");
				  System.out.println("The Status[1] is " +s);
				  
    String response1=given().log().all().header("Content-Type","application/json")
			  .queryParam("status", "sold")
    		  .when().get("/pet/findByStatus")
			  .then().assertThat().statusCode(200).extract().response().asString();
			  JsonPath js = new JsonPath(response1);
			  System.out.println("The Update pet Status : " +response1);
			  String o=js.get("category[0].status");
			  System.out.println("The category[0].status is " +s);
			
  String response3=given().log().all().header("Content-Type","application/json")
			  .queryParam("status", "pending")
			  .when().get("/pet/findByStatus")
			  .then().assertThat().statusCode(200).extract().response().asString();
			  JsonPath js1 = new JsonPath(response3);
		     System.out.println("The Update pet Status : " +response3);		
		     
	String updatepet= given().log().all().header("Content-Type","application/json")
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
		     		"  \"status\": \"sold\"\r\n" + 
		     		"}")
		     .when().put("/pet")
		     .then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath up=new JsonPath(updatepet);
	System.out.println(up);
	
	}
}
