package ch13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class BackPressureTest {

    @Test
    void generateNumberTest() {
        StepVerifier.create(BackPressureExample.generateNumber(), 1L)
                .thenConsumeWhile(num -> num > 0)
                .verifyComplete(); // 예외 발생
    }

    @Test
    void generateNumberTest2() {
        StepVerifier.create(BackPressureExample.generateNumber(), 1L)
                .thenConsumeWhile(num -> num > 0)
                .expectError()
                .verifyThenAssertThat()
                .hasDroppedElements();
    }
}
