package reactor.ch10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
public class PublishOn {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of(1, 3, 5, 7))
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))
                .publishOn(Schedulers.parallel()) // 이 메서드를 기준으로 다운스트림으로 emit하는 데이터의 스레드를 변경
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(500L);
    }
}
