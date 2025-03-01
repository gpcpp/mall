package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "seckill_product")
@ApiModel(description = "秒杀商品")
public class SeckillProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "秒杀id")
    private Integer seckillId;
    @ApiModelProperty(value = "商品id")
    private Integer productId;
    @ApiModelProperty(value = "秒杀价格")
    private Double seckillPrice;
    @ApiModelProperty(value = "秒杀库存")
    private Integer seckillStock;
    @ApiModelProperty(value = "秒杀时间")
    private Integer timeId;
}
