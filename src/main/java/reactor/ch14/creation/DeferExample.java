package reactor.ch14.creation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class DeferExample {
    public static void main(String[] args) throws InterruptedException {
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now()); // Hot Publisher
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));
        Thread.sleep(2000);
        justMono.subscribe(data -> log.info("# onNext just1: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer1: {}", data));
        Thread.sleep(2000);
        justMono.subscribe(data -> log.info("# onNext just2: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer2: {}", data));

        log.info("# start: {}", LocalDateTime.now());
        Mono.just("Hello")
                .delayElement(Duration.ofSeconds(3))
//                .switchIfEmpty(sayDefault())
                .switchIfEmpty(Mono.defer(DeferExample::sayDefault)) // 지연
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(3000);
    }

    private static Mono<String> sayDefault() {
        log.info("# Say Hi");
        return Mono.just("Hi");
    }
}
