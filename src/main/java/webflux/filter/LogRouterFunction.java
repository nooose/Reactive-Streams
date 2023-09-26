package webflux.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class LogRouterFunction {

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(GET("/v1/router/books/{bookId}"), this::getBookName)
                .filter(new LogFunctionFilter());
    }

    private Mono<ServerResponse> getBookName(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Advanced Reactor"), String.class);
    }
}
