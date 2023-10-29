package steps;

import data.BooksData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.RequestSpec;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BooksSteps {

    RequestSpecification requestSpecification;
    RequestSpecification httpRequest;
    JsonPath booksJsonPath;

    @Step("create request specification and pass it to httpRequest spec")
    public BooksSteps createRequestSpecification() {
        requestSpecification = RequestSpec.getRequestSpecification(BooksData.baseUri, BooksData.basePath).build();
        httpRequest = RestAssured.given().spec(requestSpecification);
        return this;
    }

    @Step("extract response with jsonPath method")
    public BooksSteps extractResponse() {
        Response response = httpRequest.get();

        booksJsonPath = response.then().extract().jsonPath();

        System.out.println("extracted json path.");
        return this;
    }

    @Step("validate that books pages are less than 1000")
    public BooksSteps validateBooksPagesCount() {
        List<Integer> booksPages = booksJsonPath.getList("books.pages");

        assertThat(booksPages, everyItem(lessThan(BooksData.maxBookPages)));

        System.out.println("validate books pages count.");
        return this;
    }

    @Step("validate first and second books authors")
    public BooksSteps validateBooksAuthors() {
        String expectedFirstBookAuthor = BooksData.expectedFirstBookAuthor;
        String expectedSecondBookAuthor = BooksData.expectedSecondBookAuthor;

        List<String> actualBooksAuthors = booksJsonPath.getList(
                "books[" + BooksData.firstBookIndex + "," + BooksData.secondBookIndex + "].author"
        );

        assertThat(actualBooksAuthors, contains(expectedFirstBookAuthor, expectedSecondBookAuthor));

        System.out.println("validate first and second books authors.");
        return this;
    }

}
