package ch13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class StepVerifierGeneralTest {

    @Test
    void sayHelloReactorTest() {
        StepVerifier.create(Mono.just("Hello Reactor"))
                .expectNext("Hello Reactor") // emit 데이터 기대
                .expectComplete() // onComplete Signal 기대
                .verify();
    }

    @Test
    void sayHelloTest() {
        StepVerifier.create(GeneralExample.sayHello())
                .expectSubscription()
                .as("# expect subscription") // 테스트 실패 시 보여질 description 메시지
                .expectNext("Hello")
                .as("# expect Hello")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }

    @Test
    void divideTwoTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier.create(GeneralExample.divideByTwo(source))
                .expectSubscription()
//                .expectNext(1)
//                .expectNext(2)
//                .expectNext(3)
//                .expectNext(4)
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }

    @Test
    void takeNumberTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier.create(GeneralExample.takeNumber(source, 500), StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498)
                .expectNext(499)
                .verifyComplete();
    }
}
