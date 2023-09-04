package reactor.ch08;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class Backpressure {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnNext(data -> log.info("# doOnRequest: {}", data))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        log.info("# Start");
                        request(1); // 구독 시점 최초 데이터 요청 개수 제어
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        Thread.sleep(2000L);
                        log.info("# hookOnNext: {}", value);
                        request(1);
                    }
                });
    }
}
