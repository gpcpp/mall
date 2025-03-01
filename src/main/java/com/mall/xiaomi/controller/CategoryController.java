package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.Category;
import com.mall.xiaomi.service.CategoryService;
import com.mall.xiaomi.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/category")
@Api(value = "CategoryController", tags = "获取分类接口")
public class CategoryController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CategoryService categoryService;
    @ApiOperation(value = "获取分类接口")
    @GetMapping("")
    public ResultMessage category() {
        List<Category> categories = categoryService.getAll();
        resultMessage.success("001", categories);
        return resultMessage;
    }

}
