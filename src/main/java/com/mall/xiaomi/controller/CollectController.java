package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.Product;
import com.mall.xiaomi.service.CollectService;
import com.mall.xiaomi.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collect")
@Api(value = "收藏模块", tags = "获取收藏接口")
public class CollectController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CollectService collectService;
    @ApiOperation(value = "添加收藏")
    @PostMapping("/user/{productId}/{userId}")
    public ResultMessage addCollect(@PathVariable String userId, @PathVariable String productId) {
        collectService.addCollect(userId, productId);
        resultMessage.success("001", "商品收藏成功");
        return resultMessage;
    }
    @ApiOperation(value = "获取收藏")
    @GetMapping("/user/{userId}")
    public ResultMessage getCollect(@PathVariable String userId) {
        List<Product> collects = collectService.getCollect(userId);
        resultMessage.success("001", collects);
        return resultMessage;
    }
    @ApiOperation(value = "删除收藏")
    @DeleteMapping("/user/{productId}/{userId}")
    public ResultMessage deleteCollect(@PathVariable String productId, @PathVariable String userId) {
        collectService.deleteCollect(userId, productId);
        resultMessage.success("001", "删除收藏成功");
        return resultMessage;
    }
}
