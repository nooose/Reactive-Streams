package reactor.ch05;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

public class MonoExample {
    public static void main(String[] args) {
        System.out.println("예제-1");
        Mono.just("Hello Reactor")
                .subscribe(System.out::println);

        System.out.println("예제-2");
        Mono.empty() // onComplete 시그널 전송
                .subscribe(
                        none -> System.out.println("# emitted onNext signal"),
                        error -> {
                        },
                        () -> System.out.println("# emitted onComplete signal")
                );

        System.out.println("예제-3");
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        Mono.just(
                restTemplate.exchange(worldTimeUri,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaders),
                        String.class)
        ).map(
                HttpEntity::getBody
        ).subscribe(
                data -> System.out.println("# emitted data: " + data),
                System.out::println,
                () -> System.out.println("# emitted onComplete signal")
        );
    }
}
