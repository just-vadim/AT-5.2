package ru.netology.ibank;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.util.User;

import static io.restassured.RestAssured.given;
import static ru.netology.util.DataGenerator.generateByNamePasswordStatus;

public class AuthTest {

    Gson gson = new Gson();
    User regData = generateByNamePasswordStatus();

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost")
        .setPort(9999)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();

    @BeforeAll
    static void setUpAll() {
        given()
            .spec(requestSpec)
        .when()
            .post()
        .then()
            .statusCode(200);
    }

    @Test
    void shouldRequest() {
        given()
            .body("test");
    }
}
