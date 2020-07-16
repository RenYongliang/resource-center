package com.ryl.res.base;

import java.io.Serializable;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 10:36:06
 */
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private String message;
    private Integer status;
    private T data;

    public ResultModel() {
        this.status = ResultStatus.SUCCESS.getCode();
    }

    public ResultModel(boolean success, Integer status, String message, T data) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultModel(ResultStatus resultStatus) {
        this.success = true;
        this.status = resultStatus.getCode();
        this.message = resultStatus.getMessage();
    }

    public ResultModel(ResultStatus resultStatus, String message) {
        this.success = resultStatus.getCode().equals(200) ? true : false;
        this.status = resultStatus.getCode();
        this.message = message;
    }

    public ResultModel(ResultStatus resultStatus, T data) {
        this.success = resultStatus.getCode().equals(200) ? true : false;
        this.status = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    public ResultModel(ResultStatus resultStatus, T data, String message) {
        this.success = resultStatus.getCode().equals(200) ? true : false;
        this.status = resultStatus.getCode();
        this.message = message;
        this.data = data;
    }

    public static ResultModel success() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel success(String message) {
        return new ResultModel(ResultStatus.SUCCESS,message);
    }

    public static <T> ResultModel success(T data) {
        return new ResultModel(ResultStatus.SUCCESS,data);
    }

    public static <T> ResultModel success(T data, String message) {
        return new ResultModel(ResultStatus.SUCCESS, data, message);
    }

    public static ResultModel fail() {
        return new ResultModel(ResultStatus.FAILURE);
    }

    public static ResultModel fail(String message) {
        return new ResultModel(ResultStatus.FAILURE,message);
    }

    public static <T> ResultModel fail(T data) {
        return new ResultModel(ResultStatus.FAILURE,data);
    }

    public static <T> ResultModel fail(T data, String message) {
        return new ResultModel(ResultStatus.FAILURE, data, message);
    }

    public static <T> ResultModel fail(ResultStatus resultStatus, T data) {
        return new ResultModel(resultStatus, data);
    }

    public static <T> ResultModel fail(ResultStatus resultStatus, T data, String message) {
        return new ResultModel(resultStatus, data, message);
    }

    public T getData() {
        return this.data;
    }

    public ResultModel setData(T data) {
        this.data = data;
        return this;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public ResultModel<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ResultModel<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ResultModel<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String toString() {
        return "ResultModel{data = " + this.data + ", success = " + this.success + ", message = '" + this.message + "', status = " + this.status + "}";
    }

}
