package ch13;

import reactor.core.publisher.Mono;

public class PublisherProbeExample {
    public static Mono<String> processTask(Mono<String> main, Mono<String> standby) {
        return main.flatMap(Mono::just)
                .switchIfEmpty(standby);
    }

    public static Mono<String> supplyMainPower() {
        return Mono.empty();
    }

    public static Mono<String> supplyStandbyPower() {
        return Mono.just("# supply Standby Power");
    }
}
