package com.mall.xiaomi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "category")
@ApiModel(description = "分类")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;
    @ApiModelProperty(value = "分类名称")
    private String categoryName;

}
