package webflux.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogFunctionFilter implements HandlerFilterFunction {

    @Override
    public Mono filter(ServerRequest request, HandlerFunction next) {
        String path = request.requestPath().value();
        return next.handle(request).doAfterTerminate(() -> {
            if (path.contains("books")) {
                log.info("path: {}, status: {}", path, request.exchange().getResponse().getStatusCode());
            }
        });
    }
}
