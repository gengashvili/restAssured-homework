package steps;

import data.ContinentsData;
import data.Routes;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.RequestSpec;

import java.util.List;

public class ContinentsApiSteps {
    RequestSpecification requestSpecification;
    RequestSpecification request;

    XmlPath responseXmlPath;

    public ContinentsApiSteps(){
        requestSpecification = RequestSpec
                .getRequestSpecification(Routes.CONTINENTS_BASE_URI, Routes.CONTINENTS_BASE_PATH, ContentType.XML)
                .build();

        request = RestAssured.given().spec(requestSpecification);
    }

    @Step
    public ContinentsApiSteps getAndSetResponseXmlPath() {
        responseXmlPath = request.get().xmlPath();

        return this;
    }

    @Step
    public ContinentsApiSteps validateNameCount() {
        int actualSize = responseXmlPath.getList("ArrayOftContinent.tContinent.sName").size();
        int expectedSize = ContinentsData.expectedSNameCount;

        Assert.assertEquals(actualSize, expectedSize);

        return this;
    }

    @Step
    public ContinentsApiSteps validateNameValues() {
        List<String> actualNames = responseXmlPath.getList("ArrayOftContinent.tContinent.sName");
        List<String> expectedNames = ContinentsData.expectedContinentNames;

        Assert.assertEquals(actualNames, expectedNames);

        return this;
    }

    @Step
    public ContinentsApiSteps validateANContinentName() {
        String actualName = responseXmlPath.getString("ArrayOftContinent.tContinent.find { it.sCode == 'AN' }.sName");
        String expectedName = ContinentsData.expectedANContinentName;

        Assert.assertEquals(actualName, expectedName);

        return this;
    }

    @Step
    public ContinentsApiSteps validateLastContinentName() {
        String actualName = responseXmlPath.getString("ArrayOftContinent.tContinent[-1].sName");
        String expectedName = ContinentsData.expectedContinentNames.get(ContinentsData.expectedContinentNames.size() - 1);

        Assert.assertEquals(actualName, expectedName);

        return this;
    }

}
