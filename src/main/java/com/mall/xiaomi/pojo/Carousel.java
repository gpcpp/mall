package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "carousel")
@ApiModel(description = "轮播图")
public class Carousel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @Column(name = "carousel_id")
    @ApiModelProperty(value = "轮播图id")
    private Integer carouselId;

    @Column(name = "img_path")
    @ApiModelProperty(value = "轮播图路径")
    private String imgPath;

    @Column(name = "describes")
    @ApiModelProperty(value = "轮播图描述")
    private String describes;

}
