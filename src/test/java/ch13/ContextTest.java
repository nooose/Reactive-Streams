package ch13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ContextTest {
    @Test
    void getSecretMessageTest() {
        Mono<String> source = Mono.just("hello");

        StepVerifier.create(
                        ContextExample.getSecretMessage(source)
                                .contextWrite(context -> context.put("secretMessage", "Hello, Reactor"))
                                .contextWrite(context -> context.put("secretKey", "aGVsbG8="))
                ).expectSubscription()
                .expectAccessibleContext()
                .hasKey("secretKey")
                .hasKey("secretMessage")
                .then()
                .expectNext("Hello, Reactor")
                .expectComplete()
                .verify();
    }
}
