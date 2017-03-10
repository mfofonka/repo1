package hello;

public interface BookRepository {

    Book getByIsbn(String isbn);

    Book loadBooks(String isbn);

    Book updateBook(String isbn);

}
