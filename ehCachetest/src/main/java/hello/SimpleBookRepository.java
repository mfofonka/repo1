package hello;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#isbn")
    public Book loadBooks(String isbn) {
        return new Book(isbn, "Some book");
    }

    @Override
    @CachePut("books")
    public Book updateBook(String isbn) {
        return new Book(isbn, "Some book v2");
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            final long time = 5000L;
            Thread.sleep(time);
        } catch (final InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
