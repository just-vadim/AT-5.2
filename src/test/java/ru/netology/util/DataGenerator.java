package ru.netology.util;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator(){}

    static Gson gson = new Gson();

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost")
        .setPort(9999)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();

    private static void setUpUser(User user) {
        given()
            .spec(requestSpec)
            .body(gson.toJson(user))
        .when()
            .post("/api/system/users")
        .then()
            .statusCode(200);
    }

    public static String generateLogin() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().username();
    }

    public static String generatePass() {
        Faker faker = new Faker(new Locale("en"));
        return faker.internet().password(8,12, true, false, true);
    }

    public static User generateUserInfo(boolean isActive) {
        return new User(
                generateLogin(),
                generatePass(),
                (isActive) ? "active" : "blocked");
    }

    public static User generateValidUser(boolean isActive) {
        User user = generateUserInfo(isActive);
        setUpUser(user);
        return user;
    }

    public static User generateUserWithInvalidLogin(boolean isActive) {
        User user = generateUserInfo(isActive);
        setUpUser(user);
        return new User(
                generateLogin(),
                user.getPassword(),
                (isActive) ? "active" : "blocked");
    }

    public static User generateUserWithInvalidPass(boolean isActive) {
        User user = generateUserInfo(isActive);
        setUpUser(user);
        return new User(
                user.getName(),
                generatePass(),
                (isActive) ? "active" : "blocked");
    }
}