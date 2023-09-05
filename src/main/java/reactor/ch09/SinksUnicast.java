package reactor.ch09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Slf4j
public class SinksUnicast {
    public static void main(String[] args) {
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast() // One to One
                .onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();
        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1 {}", data));
        unicastSink.emitNext(3, FAIL_FAST);
    }
}
