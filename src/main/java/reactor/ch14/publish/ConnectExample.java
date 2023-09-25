package reactor.ch14.publish;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ConnectExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> flux = Flux.range(1, 5)
                .delayElements(Duration.ofMillis(300L))
                .publish()
                .autoConnect(2);

        Thread.sleep(500L);
        flux.subscribe(data -> log.info("# subscriber1: {}", data));

        Thread.sleep(200L);
        flux.subscribe(data -> log.info("# subscriber2: {}", data));

        Thread.sleep(1000L);
        flux.subscribe(data -> log.info("# subscriber3: {}", data));
        Thread.sleep(2000L);
    }
}
