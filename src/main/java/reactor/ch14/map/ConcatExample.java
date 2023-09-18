package reactor.ch14.map;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ConcatExample {
    public static void main(String[] args) {
        Flux.concat(Flux.just(1, 2, 3), Flux.just(4, 5), Flux.just(6, 7))
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
