package steps;

import data.PersonData;
import data.Routes;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import utils.RequestSpec;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FindPersonApiSteps {

    RequestSpecification requestSpecification;
    RequestSpecification request;
    XmlPath responseXmlPath;

    public FindPersonApiSteps() {
        requestSpecification = RequestSpec
                .getRequestSpecification(Routes.FIND_PERSON_BASE_URI, Routes.FIND_PERSON_BASE_PATH, ContentType.XML)
                .build();

        request = RestAssured.given().spec(requestSpecification).header("SOAPAction", Routes.FIND_PERSON_SOAP_ACTION);
    }

    @Step
    public FindPersonApiSteps getAndSetResponseXmlPath() throws IOException {
        File file = new File("src/main/resources/personAPI.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

        Response response = request.given().body(requestBody).post();
        responseXmlPath = response.xmlPath();

        return this;
    }

    @Step
    public FindPersonApiSteps validateHomeStreet() {
        String actualHomeStreet = responseXmlPath.getString("Envelope.Body.FindPersonResponse.FindPersonResult.Home.Street");
        String expectedHomeStreet = PersonData.expectedHomeStreet;

        Assert.assertEquals(actualHomeStreet, expectedHomeStreet);

        return this;
    }

    @Step
    public FindPersonApiSteps validateOfficeZip() {
        int actualOfficeZip = responseXmlPath.getInt("Envelope.Body.FindPersonResponse.FindPersonResult.Office.Zip");
        int expectedOfficeZip = PersonData.expectedOfficeZip;

        Assert.assertEquals(actualOfficeZip, expectedOfficeZip);

        return this;
    }

}
