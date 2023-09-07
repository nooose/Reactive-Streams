package reactor.ch11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextFeature1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Context 특성 1");
        final String key1 = "company";
        Mono<String> mono = Mono.deferContextual(ctx -> Mono.just("Company: " + " " + ctx.getOrDefault(key1, "empty")))
                .publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# Subscribe1 onNext: {}", data));
        mono.contextWrite(context -> context.put(key1, "Microsoft"))
                .subscribe(data -> log.info("# Subscribe2 onNext: {}", data));
        mono.subscribe(data -> log.info("# Subscribe3 onNext: {}", data));
        Thread.sleep(100L);
    }
}
