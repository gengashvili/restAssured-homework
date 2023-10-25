package steps;

import data.BooksData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class BooksSteps {

    static final String baseUri = "https://bookstore.toolsqa.com/BookStore/v1";
    @Step("get response from books api and return for future uses")
    public Response getResponse() {
        Response response = RestAssured.get(baseUri + "/Books");

        System.out.println("got response from books api");
        return response;
    }

    @Step("validate that books pages are less than 1000")
    public void validateBookPagesCount(Response response) {
        response
                .then()
                .assertThat()
                .body("books.pages", everyItem(lessThan(BooksData.maxBookPages)));

        System.out.println("validate books page count");
    }

    @Step("validate first and second books authors")
    public void validateBookAuthors(Response response) {
        response
                .then()
                .assertThat()
                .body("books["+ BooksData.firstBookIndex +"].author", equalTo(BooksData.expectedFirstBookAuthor))
                .body("books["+ BooksData.secondBookIndex +"].author", equalTo(BooksData.expectedSecondBookAuthor));

        System.out.println("validate books authors");
    }

}
