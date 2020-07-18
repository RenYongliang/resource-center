package com.ryl.res.enums;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 17:16:13
 */
public enum ServiceExceptionEnum {

    /**
     * SPU相关 错误码30000-39999
     */
    SPU_NOT_FOUND(30001,"商品没有找到"),
    SPU_STOCK_OUT(30002,"商品库存不足")
    ;

    private Integer code;
    private String message;

    ServiceExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
