package webflux.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import webflux.application.BookService;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

//    @PostMapping("/v2/books")
//    public Mono<BookResponse> postBook(@RequestBody Mono<BookRequest> request) {
//        Mono<BookRequest> book = bookService.createBook(request);
//        return book.flatMap(it -> Mono.just(BookResponse.from(it)));
//    }
}
