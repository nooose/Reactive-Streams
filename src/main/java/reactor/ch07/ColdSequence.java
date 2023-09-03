package reactor.ch07;

import reactor.core.publisher.Flux;

import java.util.List;


public class ColdSequence {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> coldFlux = Flux.fromIterable(List.of("Korea", "Japan", "Chinese"))
                .map(String::toLowerCase);
        coldFlux.subscribe(country -> System.out.println("# Subscriber1: " + country));
        System.out.println("--------------");
        Thread.sleep(2000L);
        coldFlux.subscribe(country -> System.out.println("# Subscriber2: " + country));
    }
}
