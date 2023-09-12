package ch13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class PublisherProbeTest {

    @Test
    void publisherProbeTest() {
        PublisherProbe<String> probe = PublisherProbe.of(PublisherProbeExample.supplyStandbyPower());

        StepVerifier.create(PublisherProbeExample.processTask(
                        PublisherProbeExample.supplyMainPower(),
                        probe.mono())
                ).expectNextCount(1)
                .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }
}
