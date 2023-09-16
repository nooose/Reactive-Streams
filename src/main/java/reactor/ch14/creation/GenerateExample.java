package reactor.ch14.creation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@Slf4j
public class GenerateExample {
    public static void main(String[] args) {
        // generate()는 데이터를 동기적으로 한 번에 한 건씩 emit이 가능

        Flux.generate(() -> 0, (state, sink) -> {
                    sink.next(state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return ++state;
                })
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> {},
                        () -> log.info("onComplete"));

        final int dan = 3;
        Flux.generate(() -> Tuples.of(dan, 1), (state, sink) -> {
                    sink.next(state.getT1() + " * " + state.getT2() + " = " + state.getT1() * state.getT2());
                    if (state.getT2() == 9) {
                        sink.complete();
                    }
                    return Tuples.of(state.getT1(), state.getT2() + 1);
                }, state -> log.info("# 구구단 {}단 종료", state.getT1()))
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
