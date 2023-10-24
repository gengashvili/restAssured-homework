package data;

public class BooksData {
    public static final int
            firstBookIndex = 0,
            secondBookIndex = 1;

    public static final String
            expectedFirstBookAuthor = "Richard E. Silverman",
            expectedSecondBookAuthor = "Addy Osmani";

    public static String firstBookIsbn, secondBookIsbn;

    public static void setBookIsbn(int index, String bookIsbn) {
        if (index == 0) {
            firstBookIsbn = bookIsbn;
        } else if(index == 1) {
            secondBookIsbn = bookIsbn;
        } else {
            System.out.println("index is not valid");
        }
    }

}
