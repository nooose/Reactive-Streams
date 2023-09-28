package webflux.ui;

import webflux.application.Book;

public record BookResponse(
        String name
) {

    public static BookResponse from(Book book) {
        return new BookResponse(book.name());
    }
}
