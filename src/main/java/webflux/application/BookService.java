package webflux.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import webflux.domain.Book;
import webflux.domain.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public Mono<Book> createBook(Book book) {
        return verifyExistTitle(book.getTitle())
                .then(bookRepository.save(book));
    }

    public Mono<Book> updateBook(Long id, Book book) {
        return findVerifiedBook(id)
                .map(findBook -> {
                    findBook.changeTitle(book.getTitle());
                    return findBook;
                })
                .flatMap(bookRepository::save);
    }

    public Mono<Book> findBook(Long bookId) {
        return findVerifiedBook(bookId);
    }


    public Mono<List<Book>> findBooks() {
        return bookRepository.findAll().collectList();
    }

    private Mono<Void> verifyExistTitle(String title) {
        return bookRepository.findByTitle(title)
                .flatMap(findBook -> {
                    if (findBook != null) {
                        return Mono.error(new IllegalArgumentException());
                    }
                    return Mono.empty();
                });
    }

    private Mono<Book> findVerifiedBook(Long bookId) {
        return bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException()));
    }
}
