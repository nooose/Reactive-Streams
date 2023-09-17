package reactor.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Slf4j
public class TakeExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .take(3)
                .subscribe(data -> log.info("# [1] onNext: {}", data));
        Thread.sleep(4000);

        Flux.interval(Duration.ofSeconds(1))
                .take(Duration.ofMillis(2500))
                .subscribe(data -> log.info("# [2] onNext: {}", data));
        Thread.sleep(3000);

        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .takeLast(2)
                .subscribe(data -> log.info("# [3] onNext: {}", data));
        Thread.sleep(3000);
    }
}
