package reactor.ch14.creation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class JustOrEmptyExample {
    public static void main(String[] args) throws InterruptedException {
        Mono.justOrEmpty(null)
                .subscribe(data -> {},
                        error -> {},
                        () -> log.info("# onComplete"));
    }
}
