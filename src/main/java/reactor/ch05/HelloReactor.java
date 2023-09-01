package reactor.ch05;

import reactor.core.publisher.Flux;

public class HelloReactor {
    public static void main(String[] args) {
        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence.map(String::toLowerCase)
                .subscribe(System.out::println);
    }
}
