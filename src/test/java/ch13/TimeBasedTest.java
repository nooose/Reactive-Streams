package ch13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class TimeBasedTest {

    @Test
    void getCovid19CountTest() {
        StepVerifier.withVirtualTime(() -> TimeBasedExample.getCOVID19Count(Flux.interval(Duration.ofHours(1)).take(1)
                        )
                ).expectSubscription()
                .then(() -> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(1))) // 시간을 당길 수 있다.
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }

    @Test
    void getCovid19CountTimeOutTest() {
        StepVerifier.withVirtualTime(() -> TimeBasedExample.getCOVID19Count(Flux.interval(Duration.ofMinutes(1)).take(1)
                        )
                ).expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3)); // 시간내로 끝나지 않는다면 검증이 실패된다.
    }

    @Test
    void getVoteCountTest() {
        StepVerifier.withVirtualTime(() -> TimeBasedExample.getVoteCount(Flux.interval(Duration.ofMinutes(1))
                        )
                ).expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }
}
