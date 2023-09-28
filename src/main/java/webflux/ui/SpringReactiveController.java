package webflux.ui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class SpringReactiveController {

    @GetMapping("/book")
    public Mono<String> getBookName() throws InterruptedException {
        Thread.sleep(500L);
        String bookName = "noose";
        log.info("# book: {}", bookName);
        return Mono.just(bookName);
    }
}
