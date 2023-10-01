package webflux.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<?> routeBook(BookHandlerV5 handler) {
        return route()
                .GET("/v5/books/{bookId}", handler::getBook)
                .POST("/v5/books", handler::createBook)
                .build();
    }
}
