package reactor.ch09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Slf4j
public class SinksMulticast {
    public static void main(String[] args) {
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast() // One to Many
                .onBackpressureBuffer(); // Warm up 특징을 가지는 Hot Sequence로 동작
        Flux<Integer> fluxView = multicastSink.asFlux();
        multicastSink.emitNext(1, FAIL_FAST);
        multicastSink.emitNext(2, FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1 {}", data));
        fluxView.subscribe(data -> log.info("# Subscriber2 {}", data));
        multicastSink.emitNext(3, FAIL_FAST);
    }
}
