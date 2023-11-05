package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
public class RequestSpec {

    public static RequestSpecBuilder getRequestSpecification(String baseUri, String basePath) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .addFilter(new AllureRestAssured());
    }

}
