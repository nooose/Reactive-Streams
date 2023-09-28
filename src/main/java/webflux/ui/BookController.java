package webflux.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import webflux.application.Book;
import webflux.application.BookService;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @PostMapping("/v2/books")
    public Mono<BookResponse> postBook(@RequestBody Mono<Book> request) {
        Mono<Book> book = bookService.createBook(request);
        return book.flatMap(it -> Mono.just(BookResponse.from(it)));
    }
}
