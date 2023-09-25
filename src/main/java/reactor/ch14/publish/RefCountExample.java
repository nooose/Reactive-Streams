package reactor.ch14.publish;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class RefCountExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<Long> publisher = Flux.interval(Duration.ofMillis(500))
//                .publish().autoConnect(1)
                .publish()
                .refCount(1);
        Disposable disposable = publisher.subscribe(data -> log.info("# subscriber 1: {}", data));

        Thread.sleep(2100L);
        disposable.dispose();

        publisher.subscribe(data -> log.info("# subscriber 2: {}", data));
        Thread.sleep(2500L);
    }
}
