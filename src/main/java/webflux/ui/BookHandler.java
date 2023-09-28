package webflux.ui;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import webflux.application.Book;

import java.net.URI;

@Component
public class BookHandler {

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .flatMap(book -> ServerResponse.created(URI.create("/v1/books" + 1))
                        .build());
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("bookId"));
        Book book = new Book(String.valueOf(id));
        return ServerResponse.ok().bodyValue(book).switchIfEmpty(ServerResponse.notFound().build());
    }
}
