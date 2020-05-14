package com.ryl.res.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ryl
 * @description:
 * @date: 2020-04-22 09:49:33
 */
@Data
public class User implements Serializable {

    private Long userId;

    private String name;

    private String phone;
}
