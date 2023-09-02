package reactor.ch05;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

public class FluxExample {
    public static void main(String[] args) {
        System.out.println("예제-1");
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(System.out::println);

        System.out.println("예제-2");
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(System.out::println);

        System.out.println("예제-3");
        Flux<String> flux = Mono.justOrEmpty("Noose") // justOrEmpty(): null이라면 empty() Operator를 호출
                .concatWith(Mono.justOrEmpty("Jh"))
                .concatWith(Flux.just("A", "B"));
        flux.subscribe(System.out::println);

        System.out.println("예제-4");
        Flux.concat(
                Mono.just("Noose"),
                Mono.just("Jh"),
                Flux.just("A", "B")
        ).collectList().subscribe(System.out::println);
    }
}
