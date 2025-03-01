package com.mall.xiaomi.controller;

import com.github.pagehelper.PageInfo;
import com.mall.xiaomi.pojo.Product;
import com.mall.xiaomi.pojo.SeckillProduct;
import com.mall.xiaomi.pojo.SeckillTime;
import com.mall.xiaomi.service.SeckillProductService;
import com.mall.xiaomi.util.ResultMessage;
import com.mall.xiaomi.vo.SeckillProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seckill/product")
@Api(value = "秒杀商品管理", tags = "获取秒杀商品接口")
public class SeckillProductController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private SeckillProductService seckillProductService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "获取秒杀商品")
    @GetMapping("/time/{timeId}")
    public ResultMessage getProduct(@PathVariable String timeId) {
        List<SeckillProductVo> seckillProductVos = seckillProductService.getProduct(timeId);
        resultMessage.success("001", seckillProductVos);
        return resultMessage;
    }

    @ApiOperation(value = "获取秒杀商品详情")
    @GetMapping("/{seckillId}")
    public ResultMessage getSeckill(@PathVariable String seckillId) {
        SeckillProductVo seckillProductVo = seckillProductService.getSeckill(seckillId);
        resultMessage.success("001", seckillProductVo);
        return resultMessage;
    }
    @ApiOperation(value = "获取秒杀时间")
    @GetMapping("/time")
    public ResultMessage getTime() {
        List<SeckillTime> seckillTimes = seckillProductService.getTime();
        resultMessage.success("001", seckillTimes);
        return resultMessage;
    }

    @ApiOperation(value = "添加秒杀商品")
    @PostMapping("")
    public ResultMessage addSeckillProduct(@RequestBody SeckillProduct seckillProduct) {
        seckillProductService.addSeckillProduct(seckillProduct);
        resultMessage.success("001", "添加成功");
        return resultMessage;
    }

    @ApiOperation(value = "秒杀商品")
    @PostMapping("/seckill/{seckillId}")
    public ResultMessage seckillProduct(@PathVariable String seckillId, @CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        seckillProductService.seckillProduct(seckillId, userId);
        resultMessage.success("001", "秒杀成功");
        return resultMessage;
    }
    @ApiOperation(value = "删除秒杀商品")
    @DeleteMapping ("/seckill/{seckillId}")
    public ResultMessage deleteCollect(@PathVariable String seckillId) {
        seckillProductService.deleteSeckillProduct(seckillId);
        resultMessage.success("001", "删除秒杀商品成功");
        return resultMessage;
    }
}
