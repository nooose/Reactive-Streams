package reactor.ch14.creation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class UsingExample {
    public static void main(String[] args) throws InterruptedException {
        Path path = Paths.get(filePath());
        Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close)
                .subscribe(log::info);
    }

    private static String filePath() {
        return System.getProperty("user.dir") + "/src/main/java/reactor/ch14/creation/example.txt";
    }
}
