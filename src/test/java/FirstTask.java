import data.BooksData;
import data.BooksDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static org.hamcrest.Matchers.equalTo;

public class FirstTask {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1";
    }

    @Test(dataProvider = "indexProvider", dataProviderClass = BooksDataProvider.class)
    public void getBooksByIndex(int index, String expectedBookAuthor) {
        Response response = RestAssured.get("/Books");

        validateResponseBody(response, "books[" + index + "].author", expectedBookAuthor);

        String isbn = response.body().path("books[" + index + "].isbn");

        BooksData.setBookIsbn(index, isbn);
    }

    @Test(
            // რაკი ამ ტესტში მიღებულ isbn-ებს პირველი ტესტიდან ვსეტავ ამიტომ გავხადე პირველ მეთოდზე დამოკიდებული,
            // რადგან მე2 მეთოდი პირველის გაშვების გარეშე ვერ გაეშვას და isbn ცარიელი არ მიიღოს dataProvider დან
            dependsOnMethods = "getBooksByIndex",
            dataProvider = "isbnProvider",
            dataProviderClass = BooksDataProvider.class
    )
    public void getBooksByIsbn(String isbn, String expectedBookAuthor) {
        Response response = RestAssured.given().param("ISBN", isbn).get("/Book");

        validateResponseBody(response, "author", expectedBookAuthor);
    }


    public void validateResponseBody(Response response, String path, String expectedValue) {
        response
                .then()
                .assertThat()
                .body(path, equalTo(expectedValue));
    }

}