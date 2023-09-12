package ch13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.List;

public class TestPublisherTest {

    @Test
    void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier.create(GeneralExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.emit(2, 4, 6, 8, 10))
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }

    @Test
    void divideByTwoErrorTest() {
        TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);

        StepVerifier.create(GeneralExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> List.of(2, 4, 6, 8, null).forEach(source::next))
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }
}
