package ch13;

import org.junit.platform.commons.util.StringUtils;
import reactor.core.publisher.Flux;

public class RecordExample {
    public static Flux<String> getCapitalizedCountry(Flux<String> source) {
        return source.map(country -> country.substring(0, 1).toUpperCase() + country.substring(1));
    }
}
