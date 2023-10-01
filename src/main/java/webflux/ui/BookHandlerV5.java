package webflux.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import webflux.application.BookRequest;
import webflux.application.BookService;
import webflux.application.BookValidator;
import webflux.domain.Book;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class BookHandlerV5 {
    private final BookValidator validator;
    private final BookService bookService;

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(BookRequest.class)
                .doOnNext(this::validate) // Operator 체인 내에서 검증
                .flatMap(book -> bookService.createBook(new Book(book.id(), book.title())))
                .flatMap(book -> ServerResponse.created(URI.create("/v5/books" + book.getId()))
                        .build());
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("bookId"));

        return request.bodyToMono(BookRequest.class)
                .doOnNext(this::validate) // Operator 체인 내에서 검증
                .flatMap(book -> bookService.updateBook(id, new Book(null, book.title())))
                .flatMap(book -> ServerResponse.created(URI.create("/v5/books" + book.getId()))
                        .build());
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("bookId"));
        return bookService.findBook(id)
                .flatMap(book -> ServerResponse.ok().bodyValue(book));
    }

    public Mono<ServerResponse> getBooks(ServerRequest request) {
        return bookService.findBooks()
                .flatMap(book -> ServerResponse.ok().bodyValue(book));
    }

    private void validate(BookRequest book) {
        Errors errors = new BeanPropertyBindingResult(book, BookRequest.class.getName());
        validator.validate(book, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }
}
