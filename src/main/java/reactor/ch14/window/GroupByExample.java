package reactor.ch14.window;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class GroupByExample {
    public static void main(String[] args) {
        System.out.println("예제 1");
        Flux.fromIterable(List.of(10, 10, 30, 40, 50, 60, 60, 70, 70, 70))
                .groupBy(num -> num)
                .flatMap(groupedFlux -> groupedFlux
                        .map(num -> num + " group")
                        .collectList()
                ).subscribe(data -> log.info("# data: {}", data));

        System.out.println("예제 2");
        Flux.fromIterable(List.of(10, 10, 30, 40, 50, 60, 60, 70, 70, 70))
                .groupBy(num -> num, num -> num + " group")
                .flatMap(Flux::collectList)
                .subscribe(data -> log.info("# data: {}", data));
    }
}
