package com.mall.xiaomi.vo;

import com.mall.xiaomi.pojo.SeckillProduct;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeckillProductVo extends SeckillProduct implements Serializable {

    private String productName;

    private Double productPrice;

    private String productPicture;

    private Long startTime;

    private Long endTime;


}
