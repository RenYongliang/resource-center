package com.ryl.res.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: ryl
 * @description:
 * @date: 2020-04-22 09:47:36
 */
@Data
public class  MessageDTO<T extends Serializable> implements Serializable {

    private String messageId;

    private T messageData;

    private LocalDateTime createTime;

}
