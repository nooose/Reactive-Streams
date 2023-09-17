package reactor.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class NextExample {
    public static void main(String[] args) {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .next() // 첫 번째 데이터만 emit
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
