package reactor.ch10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
public class SubscribeOn {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of(1, 3, 5, 7))
                .subscribeOn(Schedulers.boundedElastic()) // 스케줄러 지정
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe")) // 구독이 발생한 시점에 추가적인 어떤 처리가 필요할 때 사용
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(500L);
    }
}
