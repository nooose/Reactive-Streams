package reactor.ch08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BackpressureErrorStrategy {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Error 전략");
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureError()
                .doOnNext(data -> log.info("# doOnNext: {}", data)) // emit 디버깅 가능
                .publishOn(Schedulers.parallel()) // Sequence 일부를 별도의 쓰레드에서 실행 가능
                .subscribe(data -> {
                        try {
                            Thread.sleep(5L);
                        } catch (InterruptedException e) { }
                        log.info("# onNext: {}", data);
                    }, error -> log.error("# onError", error));
        Thread.sleep(2000L);
    }
}
