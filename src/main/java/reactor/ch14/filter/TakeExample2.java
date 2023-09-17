package reactor.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Slf4j
public class TakeExample2 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .takeUntil(data -> data >= 4)
                .subscribe(data -> log.info("# [1] onNext: {}", data));

        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .takeWhile(data -> data < 4)
                .subscribe(data -> log.info("# [2] onNext: {}", data));

    }
}
