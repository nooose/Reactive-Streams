package reactor.ch10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
public class ParallelCount {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of(1, 3, 5, 7, 9, 11, 13, 15, 17, 19))
                .parallel(4)
                .runOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(100L);
    }
}
