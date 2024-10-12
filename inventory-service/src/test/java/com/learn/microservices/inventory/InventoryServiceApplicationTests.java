package com.learn.microservices.inventory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldAddItemToInventory() {
		String addItemJson = """
								{
				  "skuCode": "samsung_galaxy",
				  "quantity": 100
				}
								""";
		var responseBodyString = RestAssured.given().contentType("application/json").body(addItemJson).when()
				.post("/api/inventory").then().log().all().statusCode(201).extract().body().asString();

		assertThat(responseBodyString, Matchers.is("Item added to inventory successfully"));
	}

	@Test
	void shouldReadInventory() {
		var response = RestAssured.given().when().get("api/inventory?skuCode=iphone_15&quantity=10").then().log().all()
				.statusCode(200).extract().response().as(Boolean.class);
		assertTrue(response);

		var falseResponse = RestAssured.given().when().get("api/inventory?skuCode=iphone_15&quantity=1000").then().log()
				.all().statusCode(200).extract().response().as(Boolean.class);
		assertFalse(falseResponse);
	}

}
