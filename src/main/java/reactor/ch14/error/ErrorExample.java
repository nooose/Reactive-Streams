package reactor.ch14.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ErrorExample {
    public static void main(String[] args) {
        System.out.println("예제 1");
        Flux.range(1, 5)
                .flatMap(num -> {
                    if ((num * 2) % 3 == 0) {
                        return Flux.error(new IllegalArgumentException("Not allowed multiple of 3"));
                    }
                    return Mono.just(num * 2);
                })
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));

        System.out.println("예제 2");
        Flux.range(1, 5)
                .flatMap(num -> {
                    if ((num * 2) % 3 == 0) {
                        return Flux.error(new IllegalArgumentException("Not allowed multiple of 3"));
                    }
                    return Mono.just(num * 2);
                })
                .onErrorReturn(0)
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));

        System.out.println("예제 3");
        Flux.range(1, 5)
                .flatMap(num -> {
                    if ((num * 2) % 3 == 0) {
                        return Flux.error(new IllegalArgumentException("Not allowed multiple of 3"));
                    }
                    return Mono.just(num * 2);
                })
                .onErrorReturn(IllegalAccessError.class, 0)
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));

        System.out.println("예제 4");
        Flux.range(1, 5)
                .flatMap(num -> {
                    if ((num * 2) % 3 == 0) {
                        return Flux.error(new IllegalArgumentException("Not allowed multiple of 3"));
                    }
                    return Mono.just(num * 2);
                })
                .onErrorResume(error -> Flux.range(10, 5))
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));

        System.out.println("예제 5");
        Flux.range(1, 5)
                .flatMap(num -> {
                    if ((num * 2) % 3 == 0) {
                        return Flux.error(new IllegalArgumentException("Not allowed multiple of 3"));
                    }
                    return Mono.just(num * 2);
                })
                .onErrorContinue((error, num) -> log.error("error: {}, num: {}", error.getMessage(), num))
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));
    }
}
