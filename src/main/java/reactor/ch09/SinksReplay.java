package reactor.ch09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Slf4j
public class SinksReplay {
    public static void main(String[] args) {
        Sinks.Many<Integer> replaySink = Sinks.many().replay() // 구독 전 데이터들도 Subscriber에게 전달
                .limit(2); // 나중에 emit된 데이터부터 Subscriber에게 전달
        Flux<Integer> fluxView = replaySink.asFlux();
        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1 {}", data));

        replaySink.emitNext(4, FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber2 {}", data));
    }
}
