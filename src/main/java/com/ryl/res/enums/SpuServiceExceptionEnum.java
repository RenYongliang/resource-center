package com.ryl.res.enums;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 17:16:13
 */
public enum SpuServiceExceptionEnum {

    SPU_NOT_FOUND(30001,"商品没有找到"),
    SPU_STOCK_OUT(30002,"商品库存不足")
    ;

    private Integer code;
    private String message;

    SpuServiceExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return null;
    }

    public String getMessage() {
        return null;
    }
}
