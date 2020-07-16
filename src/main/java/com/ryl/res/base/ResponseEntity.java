package com.ryl.res.base;

import java.io.Serializable;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 10:36:06
 */
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private String message;
    private Integer status;
    private T data;

    public ResponseEntity() {
        this.status = ResponseState.SUCCESS.getCode();
    }

    public ResponseEntity(boolean success, Integer status, String message, T data) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity(ResponseState responseState) {
        this.success = true;
        this.status = responseState.getCode();
        this.message = responseState.getMessage();
    }

    public ResponseEntity(ResponseState responseState, String message) {
        this.success = responseState.getCode().equals(200) ? true : false;
        this.status = responseState.getCode();
        this.message = message;
    }

    public ResponseEntity(ResponseState responseState, T data) {
        this.success = responseState.getCode().equals(200) ? true : false;
        this.status = responseState.getCode();
        this.message = responseState.getMessage();
        this.data = data;
    }

    public ResponseEntity(ResponseState responseState, T data, String message) {
        this.success = responseState.getCode().equals(200) ? true : false;
        this.status = responseState.getCode();
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity success() {
        return new ResponseEntity(ResponseState.SUCCESS);
    }

    public static ResponseEntity success(String message) {
        return new ResponseEntity(ResponseState.SUCCESS,message);
    }

    public static <T> ResponseEntity success(T data) {
        return new ResponseEntity(ResponseState.SUCCESS,data);
    }

    public static <T> ResponseEntity success(T data, String message) {
        return new ResponseEntity(ResponseState.SUCCESS, data, message);
    }

    public static ResponseEntity fail() {
        return new ResponseEntity(ResponseState.FAILURE);
    }

    public static ResponseEntity fail(String message) {
        return new ResponseEntity(ResponseState.FAILURE,message);
    }

    public static <T> ResponseEntity fail(T data) {
        return new ResponseEntity(ResponseState.FAILURE,data);
    }

    public static <T> ResponseEntity fail(T data, String message) {
        return new ResponseEntity(ResponseState.FAILURE, data, message);
    }

    public static <T> ResponseEntity fail(ResponseState responseState, T data) {
        return new ResponseEntity(responseState, data);
    }

    public static <T> ResponseEntity fail(ResponseState responseState, T data, String message) {
        return new ResponseEntity(responseState, data, message);
    }

    public T getData() {
        return this.data;
    }

    public ResponseEntity setData(T data) {
        this.data = data;
        return this;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public ResponseEntity<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ResponseEntity<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ResponseEntity<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String toString() {
        return "ResponseEntity{data = " + this.data + ", success = " + this.success + ", message = '" + this.message + "', status = " + this.status + "}";
    }

}
