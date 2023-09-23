package reactor.ch14.window;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class BufferExample {
    public static void main(String[] args) {
        System.out.println("예제 1");
        Flux.range(1, 95)
                .buffer(10)
                .subscribe(buffer -> log.info("# onNext: {}", buffer));

        System.out.println("예제 2");
        Flux.range(1, 20)
                .map(num -> {
                    try {
                        if (num < 10) {
                            Thread.sleep(100L);
                            return num;
                        }
                        Thread.sleep(300L);
                    } catch (InterruptedException e) {}
                    return num;
                })
                .bufferTimeout(3, Duration.ofMillis(400L))
                .subscribe(buffer -> log.info("# onNext: {}", buffer));
    }
}
