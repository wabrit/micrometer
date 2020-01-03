package io.micrometer.core.instrument.binder.http;

import io.micrometer.core.annotation.Incubating;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Incubating(since = "1.4.0")
public class HttpRequestTags {
    private static final Tag EXCEPTION_NONE = Tag.of("exception", "None");

    private static final Tag STATUS_UNKNOWN = Tag.of("status", "UNKNOWN");

    private static final Tag METHOD_UNKNOWN = Tag.of("method", "UNKNOWN");

    private HttpRequestTags() {
    }

    /**
     * Creates a {@code method} tag based on the {@link HttpServletRequest#getMethod()
     * method} of the given {@code request}.
     * @param request the request
     * @return the method tag whose value is a capitalized method (e.g. GET).
     */
    public static Tag method(HttpServletRequest request) {
        return (request != null) ? Tag.of("method", request.getMethod()) : METHOD_UNKNOWN;
    }

    /**
     * Creates a {@code status} tag based on the status of the given {@code response}.
     * @param response the HTTP response
     * @return the status tag derived from the status of the response
     */
    public static Tag status(HttpServletResponse response) {
        return (response != null) ? Tag.of("status", Integer.toString(response.getStatus())) : STATUS_UNKNOWN;
    }

    /**
     * Creates a {@code exception} tag based on the {@link Class#getSimpleName() simple
     * name} of the class of the given {@code exception}.
     * @param exception the exception, may be {@code null}
     * @return the exception tag derived from the exception
     */
    public static Tag exception(Throwable exception) {
        if (exception != null) {
            String simpleName = exception.getClass().getSimpleName();
            return Tag.of("exception", StringUtils.isNotBlank(simpleName) ? simpleName : exception.getClass().getName());
        }
        return EXCEPTION_NONE;
    }

    /**
     * Creates an {@code outcome} tag based on the status of the given {@code response}.
     * @param response the HTTP response
     * @return the outcome tag derived from the status of the response
     */
    public static Tag outcome(HttpServletResponse response) {
        Outcome outcome = (response != null) ? Outcome.forStatus(response.getStatus()) : Outcome.UNKNOWN;
        return outcome.asTag();
    }
}
