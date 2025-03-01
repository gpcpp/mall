package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "seckill_time")
@ApiModel(description = "秒杀时间段")
public class SeckillTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "秒杀时间段id")
    private Integer timeId;
    @ApiModelProperty(value = "秒杀开始时间")
    private Long startTime;
    @ApiModelProperty(value = "秒杀结束时间")
    private Long endTime;

}
