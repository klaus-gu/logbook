package org.zalando.logbook;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

final class BodyReplacerTest {

    @Test
    void shouldStopOnFirstReplacerThatReplaced() throws IOException {
        final BodyReplacer<HttpMessage> unit = BodyReplacer.composite(
                m -> m.getContentType().startsWith("text/") ? "<text>" : null,
                m -> m.getContentType().endsWith("plain") ? "<plain-text>" : null);
        final HttpMessage message = mock(HttpMessage.class);
        when(message.getContentType()).thenReturn("text/plain");

        final String body = unit.replace(message);

        assertThat(body).isEqualTo("<text>");
    }

}
