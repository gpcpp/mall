package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.Carousel;
import com.mall.xiaomi.service.CarouselService;
import com.mall.xiaomi.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "轮播图管理", tags = "获取轮播图接口")
public class CarouselController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CarouselService carouselService;
    @ApiOperation(value = "获取轮播图列表")
    @GetMapping("/resources/carousel")
    public ResultMessage carousels() {
        List<Carousel> carousels = carouselService.getCarouselList();
        resultMessage.success("001", carousels);
        return resultMessage;
    }

}
