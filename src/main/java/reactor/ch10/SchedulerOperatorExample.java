package reactor.ch10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
public class SchedulerOperatorExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main 스레드 사용");
        Flux.fromIterable(List.of(1, 3, 5, 7))
                .doOnNext(data -> log.info("# doOnNext fromList: {}", data))
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter: {}", data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));

        System.out.println("publishOn() 사용");
        Flux.fromIterable(List.of(1, 3, 5, 7))
                .doOnNext(data -> log.info("# doOnNext fromList: {}", data))
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter: {}", data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(500L);

        System.out.println("publishOn() 2개 체인 사용");
        Flux.fromIterable(List.of(1, 3, 5, 7))
                .doOnNext(data -> log.info("# doOnNext fromList: {}", data))
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter: {}", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(500L);

        System.out.println("subscribeOn() publishOn() 함께 사용");
        Flux.fromIterable(List.of(1, 3, 5, 7))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> log.info("# doOnNext fromList: {}", data))
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter: {}", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(500L);

    }
}
