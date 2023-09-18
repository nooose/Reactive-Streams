package reactor.ch14.map;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ZipExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.zip(
                Flux.just(1, 2, 3, 4).delayElements(Duration.ofMillis(300L)),
                Flux.just(5, 6, 7).delayElements(Duration.ofMillis(500L))
        ).subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(2500L);

        Flux.zip(
                Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
                Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L)),
                (n1, n2) -> n1 * n2
        ).subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(2500L);

        Flux.zip(
                Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
                Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L)),
                Flux.just(7, 8, 9).delayElements(Duration.ofMillis(200L))
        ).subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(2500L);
    }
}
