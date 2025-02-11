package org.fludland.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessResponse<T> {
    private final T data;

    @JsonCreator
    public SuccessResponse(@JsonProperty("data") final T data) {
        this.data = data;
    }

    @JsonGetter
    public T getData() {
        return data;
    }
}
