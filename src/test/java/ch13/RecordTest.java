package ch13;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordTest {

    @Test
    void getCityTest() {
        StepVerifier.create(RecordExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")))
                .expectSubscription()
                .recordWith(ArrayList::new) // emit된 데이터에 대한 기록을 시작
                .thenConsumeWhile(country -> !country.isEmpty())
                .consumeRecordedWith(countries -> {
                    assertThat(countries).allMatch(country -> Character.isUpperCase(country.charAt(0)));
                })
                .expectComplete()
                .verify();
    }

    @Test
    void getCityTest2() {
        StepVerifier.create(RecordExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")))
                .expectSubscription()
                .recordWith(ArrayList::new) // emit된 데이터에 대한 기록을 시작
                .thenConsumeWhile(country -> !country.isEmpty())
                .expectRecordedMatches(countries -> countries.stream()
                        .allMatch(country -> Character.isUpperCase(country.charAt(0))))
                .expectComplete()
                .verify();
    }
}
