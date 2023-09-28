package webflux.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<?> routeBook(BookHandler handler) {
        return route()
                .GET("/v1/books/{bookId}", handler::getBook)
                .POST("/v1/books", handler::createBook)
                .build();
    }
}
