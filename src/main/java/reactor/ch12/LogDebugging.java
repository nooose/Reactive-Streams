package reactor.ch12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@Slf4j
public class LogDebugging {
    private static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }
    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(List.of("BANANAS", "APPLES", "PEARS", "MELONS"))
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .log("Fruit.Substring", Level.FINE) // DEBUG 레벨
                .map(fruits::get)
                .subscribe(log::info, error -> log.error("# onError: ", error));
    }
}
