package com.mall.xiaomi.vo;

import com.mall.xiaomi.pojo.Order;
import lombok.Data;

@Data
public class OrderVo extends Order {

    private String productName;

    private String productPicture;

}
