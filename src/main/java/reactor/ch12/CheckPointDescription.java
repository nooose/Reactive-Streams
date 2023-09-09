package reactor.ch12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CheckPointDescription {
    public static void main(String[] args) throws InterruptedException {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("zipWith.checkPoint")
                .map(num -> num + 2)
                .checkpoint("mapWith.checkPoint")
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));
    }
}
