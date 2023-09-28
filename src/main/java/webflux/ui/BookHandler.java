package webflux.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import webflux.application.Book;
import webflux.application.BookValidator;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class BookHandler {
    private final BookValidator validator;

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .doOnNext(this::validate) // Operator 체인 내에서 검증
                .flatMap(book -> ServerResponse.created(URI.create("/v1/books" + 1))
                        .build());
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("bookId"));
        Book book = new Book(String.valueOf(id));
        return ServerResponse.ok()
                .bodyValue(book)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    private void validate(Book book) {
        Errors errors = new BeanPropertyBindingResult(book, Book.class.getName());
        validator.validate(book, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }
}
