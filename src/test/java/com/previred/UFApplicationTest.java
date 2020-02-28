package com.previred;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UfApplication.class)
public class UFApplicationTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8010;
    }

	
	@org.junit.Test
    public void getValores() {
		
		given().get("api/uf").then()
		 .assertThat().statusCode(200).
        	and().
        		contentType(ContentType.JSON);

	} 
	
}

