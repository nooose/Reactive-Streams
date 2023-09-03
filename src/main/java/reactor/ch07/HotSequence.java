package reactor.ch07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;


@Slf4j
public class HotSequence {
    public static void main(String[] args) throws InterruptedException {
        List<String> singers = List.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E");
        System.out.println("# Begin concert:");

        Flux<String> concertFlux = Flux.fromIterable(singers)
                .delayElements(Duration.ofSeconds(1))
                .share(); // 여러 Subscriber가 하나의 원본 Flux를 공유, HotSequence 처럼 동작하게 하는 기능을 함
        concertFlux.subscribe(singer -> log.info("# Subscriber1 is watching: {}", singer));
        Thread.sleep(2500);
        concertFlux.subscribe(singer -> log.info("# Subscriber2 is watching: {}", singer));
        Thread.sleep(3000);
    }
}
