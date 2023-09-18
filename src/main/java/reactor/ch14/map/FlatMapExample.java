package reactor.ch14.map;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FlatMapExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.just("Good", "Bad")
                .flatMap(feeling -> Flux.just("Morning", "Afternoon", "Evening")
                        .map(time -> feeling + " " + time))
                .subscribe(log::info);

        Flux.range(2, 8)
                .flatMap(dan -> Flux.range(1, 9)
                        .publishOn(Schedulers.parallel())
                        .map(n -> dan + " * " + n + " = " + dan * n))
                .subscribe(log::info);
        Thread.sleep(100L);
    }
}
