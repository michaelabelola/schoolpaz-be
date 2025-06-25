package com.suiteonix.schoolpaz.kernel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Nix exception.
 */
public class PazException extends RuntimeException {

    /**
     * The Status.
     */
    HttpStatusCode status;
    @Nullable
    private String title;
    @Nullable
    private String detail;
    @Nullable
    private URI instance;
    @Nullable
    private Map<String, Object> properties = new HashMap<>();
    @Nullable
    private URI type;

    /**
     * Instantiates a new Nix exception.
     *
     * @param status the status
     */
    public PazException(HttpStatusCode status) {
        super(Objects.nonNull(status) ? String.valueOf(status.value()) : "");
        this.status = status;
    }

    public PazException(HttpStatusCode status, String title) {
        super(Objects.nonNull(title) ? title : "");
        this.status = status;
    }

    /**
     * Title nix exception.
     *
     * @param title the title
     * @return the nix exception
     */
    public PazException title(String title) {
        this.title = title;
        return this;
    }

    public PazException type(URI type) {
        this.type = type;
        return this;
    }

    /**
     * Detail nix exception.
     *
     * @param detail the detail
     * @return the nix exception
     */
    public PazException detail(String detail) {
        this.detail = detail;
        return this;
    }

    /**
     * Instance nix exception.
     *
     * @param instance the instance
     * @return the nix exception
     */
    public PazException instance(URI instance) {
        this.instance = instance;
        return this;
    }

    /**
     * set Properties to a new map (defaults to an empty Map).
     *
     * @param properties the properties
     * @return the nix exception
     */
    public PazException properties(Map<String, Object> properties) {
        if (properties == null)
            this.properties = new HashMap<>();
        else
            this.properties = properties;
        return this;
    }

    /**
     * Prop nix exception.
     *
     * @param key   the key
     * @param value the value
     * @return the nix exception
     */
    public PazException prop(String key, Object value) {
        if (this.properties != null && key != null)
            this.properties.put(key, value);
        return this;
    }

    /**
     * Remove prop nix exception.
     *
     * @param key the key
     * @return the nix exception
     */
    public PazException removeProp(String key) {
        if (this.properties != null && key != null)
            this.properties.remove(key);
        return this;
    }


    /**
     * Instantiates a new Nix exception.
     *
     * @param status the status
     * @param body   the body
     */
    public PazException(HttpStatusCode status, ProblemDetail body) {
        this.status = status;
        if (body == null) return;
        this.title = body.getTitle();
        this.detail = body.getDetail();
        this.instance = body.getInstance();
        this.properties = body.getProperties();
    }

    /**
     * Instantiates a new Nix exception.
     *
     * @param body the body
     */
    public PazException(ProblemDetail body) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, body);
    }

    public ErrorResponseException buildException() {
        return new ErrorResponseException(status, toProblemDetail(), null);
    }

    ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(this.status);
        if (this.type != null)
            problemDetail.setType(this.type);
        if (this.title != null)
            problemDetail.setTitle(this.title);
        if (this.detail != null)
            problemDetail.setDetail(this.detail);
        if (this.instance != null)
            problemDetail.setInstance(this.instance);
        if (this.properties != null)
            problemDetail.setProperties(this.properties);
        return problemDetail;
    }
}
