package data;

import org.testng.annotations.DataProvider;

public class BooksDataProvider {
    static String expectedFirstBookAuthor = BooksData.expectedFirstBookAuthor;
    static String expectedSecondBookAuthor = BooksData.expectedSecondBookAuthor;


    @DataProvider(name = "indexProvider")
    public static Object[][] booksIndexProvider() {
        int firstBookIndex = BooksData.firstBookIndex;
        int secondBookIndex = BooksData.secondBookIndex;

        return new Object[][] {
                { firstBookIndex, expectedFirstBookAuthor },
                { secondBookIndex, expectedSecondBookAuthor }
        };
    }

    @DataProvider(name = "isbnProvider")
    public static Object[][] booksIsbnProvider() {
        String firstBookIsbn = BooksData.firstBookIsbn;
        String secondBookIsbn = BooksData.secondBookIsbn;

        return new Object[][] {
                { firstBookIsbn, expectedFirstBookAuthor },
                { secondBookIsbn, expectedSecondBookAuthor }
        };
    }


}
