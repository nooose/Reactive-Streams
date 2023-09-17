package reactor.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
public class FilterWhenExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .filterWhen(data -> Mono.just(data > 3).publishOn(Schedulers.parallel()))
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(1000);
    }
}
