package org.example;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

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
                .statusCode(202);
    }
}
