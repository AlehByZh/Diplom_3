package org.example;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;

public class UserClient {
    @Step("delete courier")
    public void deleteCourier(String accessToken) {
        given().log().all()
                .contentType(ContentType.JSON)
                .header("authorization", accessToken)
                .baseUri(EnvConfig.BASE_URL)
                .basePath(EnvConfig.BASE_PATH)
                .when()
                .delete(EnvConfig.AUTH_PATH + "/user")
                .then().log().all()
                .assertThat()
                .statusCode(HTTP_ACCEPTED);
    }

    @Step("Creat user")
    public User createUser() {
        Faker faker = new Faker();
        var user = User.builder()
                        .email(faker.internet().emailAddress())
                        .name(faker.name().firstName())
                        .password(faker.internet().password())
                        .build();
        given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(EnvConfig.BASE_URL)
                .basePath(EnvConfig.BASE_PATH)
                .body(user)
                .when()
                .post(EnvConfig.AUTH_PATH + "/register")
                .then().log().all()
                .assertThat()
                .statusCode(HTTP_OK);
        return user;
    }
}
