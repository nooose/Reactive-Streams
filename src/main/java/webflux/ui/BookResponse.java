package webflux.ui;

import webflux.application.BookRequest;

public record BookResponse(
        String title
) {

    public static BookResponse from(BookRequest book) {
        return new BookResponse(book.title());
    }
}
