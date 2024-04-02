package com.tsk.practice.dtos;

import lombok.Getter;

@Getter
public class Response<T> {
    private String message;
    private T data;
    private Integer status;
    private boolean has_error;

    public Response(T data) {
        this.data = data;
    }

    public Response<T> message(String message) {
        this.message = message;
        return this;
    }

    public Response<T> status(Integer status) {
        this.status = status;
        return this;
    }

    public Response<T> has_error(boolean has_error) {
        this.has_error = has_error;
        return this;
    }

    public Response<T> content(T content) {
        this.data = content;
        return this;
    }
}
