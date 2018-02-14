package org.zalando.logbook.okhttp;

import okhttp3.Request;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class LocalRequestTest {

    private LocalRequest unit(final Request request) {
        return new LocalRequest(request);
    }

    @Test
    void shouldResolveLocalhost() {
        final LocalRequest unit = unit(get("http://localhost/"));

        assertThat(unit.getRemote(), is("localhost"));
    }

    private Request get(final String uri) {
        return new Request.Builder()
                .url(uri)
                .get()
                .build();
    }

}
