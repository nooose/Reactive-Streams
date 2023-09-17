package reactor.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;

@Slf4j
public class SkipExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .skip(2)
                .subscribe(data -> log.info("# [1] onNext: {}", data));
        Thread.sleep(5500L);

        Flux.interval(Duration.ofMillis(300))
                .skip(Duration.ofSeconds(1))
                .subscribe(data -> log.info("# [2] onNext: {}", data));
        Thread.sleep(2000L);
    }
}
