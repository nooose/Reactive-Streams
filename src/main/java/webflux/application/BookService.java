package webflux.application;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    public Mono<Book> createBook(Mono<Book> book) {
        return book.flatMap(Mono::just);
    }

    public Mono<Book> updateBook(Mono<Book> book) {
        return book.flatMap(Mono::just);
    }

    public Mono<Book> findBook(Long bookId) {
        return Mono.just(new Book(String.valueOf(bookId)));
    }
}
