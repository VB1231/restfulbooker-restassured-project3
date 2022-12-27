package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CrudTest extends TestBase {
@Test
    public void getAllProductsInfo() {
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .get("");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getSingleProductInfo() {
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/")
                .when()
                .get("1");
        response.then().statusCode(200);
        response.prettyPrint();
    }
@Test
    public void createProduct() {

//        List<String> courseList = new ArrayList<>();
//        courseList.add("New booking");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName("viral");
        bookingPojo.setLastName("Brahmbhatt");
        bookingPojo.setTotalprice(649);

    Response response = given()
            .baseUri("https://restful-booker.herokuapp.com/booking")
            .header("Content-Type", "application/json")
            .header("Accept","application/json")
             .when()
                 .body(bookingPojo)
            .post();
    response.then().log().all().statusCode(201);
    response.prettyPrint();
    }
    @Test
    public void deleteRecord(){
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/1")
                .header("Content-type", "application/json")
                .header("Cookie","token=abc123")
                .when()
                .delete()
                .then()
                .extract().response();
        response.then().statusCode(404);
        response.prettyPrint();

    }
    @Test
    public void patchRecord() {
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/1")
                .header("Content-type", "application/json")
                .header("Accept","application/json")
                .pathParam("firstname", "viral")
                .when()
                .patch("{firstname}");
        response.then().statusCode(200);
        response.prettyPrint();


    }}

