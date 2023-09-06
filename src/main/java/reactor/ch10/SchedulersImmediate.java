package reactor.ch10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
public class SchedulersImmediate {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of(1, 3, 5, 7))
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .publishOn(Schedulers.immediate())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(200L);
    }
}
