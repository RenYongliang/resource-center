package com.ryl.res.model.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-14 13:39:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportModel {

    /**
     * mergeVertical type：boolean  默认false  是否纵向合并内容相同的单元格
     * mergeRely     type: int[]    默认{}     合并单元格依赖关系,比如第二列合并是基于第一列 则{0}就可以了
     */

    @Excel(name = "客户名称", width = 30, mergeVertical = true)
    private String username; //客户名称

    @Excel(name = "客户名称", width = 30, mergeVertical = true, mergeRely = {0})
    private String phone; //客户手机

    @Excel(name = "产品名称", width = 30)
    private String productName; //产品名称

    @Excel(name = "产品数量", width = 30)
    private Integer num; //产品数量

    @Excel(name = "单位", width = 30)
    private String unit; //单位

    @Excel(name = "收货地址", width = 30, mergeVertical = true, mergeRely = {0})
    private String address; //收货地址

    @Excel(name = "打款金额", width = 30)
    private float price;  //打款金额
}
