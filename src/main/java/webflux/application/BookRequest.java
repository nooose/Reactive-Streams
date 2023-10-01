package webflux.application;

public record BookRequest(
        Long id,
        String title
) {
}
