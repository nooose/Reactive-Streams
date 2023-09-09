package reactor.ch12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OperatorCheckPoint {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> source = Flux.just(2, 4, 6, 8);
        Flux<Integer> other = Flux.just(1, 2, 3, 0);

        Flux<Integer> multiplySource = divide(source, other);
        Flux<Integer> plusSource = plus(multiplySource).checkpoint();

        plusSource.subscribe(
                data -> log.info("# onNext: {}", data),
                error -> log.error("# onError: {}", error)
        );
    }

    private static Flux<Integer> divide(Flux<Integer> source, Flux<Integer> other) {
        return source.zipWith(other, (x, y) -> x / y);
    }

    private static Flux<Integer> plus(Flux<Integer> source) {
        return source.map(num -> num + 2);
    }
}
