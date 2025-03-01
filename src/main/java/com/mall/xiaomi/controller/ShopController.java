package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.Shop;
import com.mall.xiaomi.service.ShopService;
import com.mall.xiaomi.util.BeanUtil;
import com.mall.xiaomi.util.CookieUtil;
import com.mall.xiaomi.util.MD5Util;
import com.mall.xiaomi.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/Shop")
@Api(value = "商家管理",tags = "获取商家接口")
public class ShopController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ShopService shopService;
    @ApiOperation(value = "商家登录")
    @PostMapping("/login")
    public ResultMessage login(@RequestBody Shop shop, HttpServletRequest request, HttpServletResponse response) {
        shop = shopService.login(shop);
        // 添加cookie，设置唯一认证
        String encode = MD5Util.MD5Encode(shop.getUsername() + shop.getPassword(), "UTF-8");
        // 进行加盐
        encode += "|" + shop.getId()+ "|" + shop.getUsername() + "|";
        CookieUtil.setCookie(request, response, "XM_TOKEN", encode, 1800);
        // 将encode放入redis中，用于认证
        try {
            redisTemplate.opsForHash().putAll(encode, BeanUtil.bean2map(shop));
            redisTemplate.expire(encode, 30 * 60, TimeUnit.SECONDS); // 设置过期时间
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将密码设为null,返回给前端
        shop.setPassword(null);
        resultMessage.success("001", "登录成功", shop);
        return resultMessage;
    }

    @ApiOperation(value = "商家注册")
    @PostMapping("/register")
    public ResultMessage register(@RequestBody Shop shop) {
        shopService.register(shop);
        resultMessage.success("001", "注册成功");
        return resultMessage;
    }

    @ApiOperation(value = "商家用户名是否可用")
    @GetMapping("/shopname/{shopname}")
    public ResultMessage shopname(@PathVariable String shopname) {
        shopService.isUserName(shopname);
        resultMessage.success("001", "可注册");
        return resultMessage;
    }

    @ApiOperation(value = "获取商家信息")
    @GetMapping("/token")
    public ResultMessage token(@CookieValue("XM_TOKEN") String token, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map = redisTemplate.opsForHash().entries(token);
        // 可能map为空 ， 即redis中时间已过期，但是cookie还存在。
        // 这个时候应该删除cookie，让用户重新登录
        if (map.isEmpty()) {
            CookieUtil.delCookie(request, token);
            resultMessage.fail("002", "账号过期,请重新登录");
            return resultMessage;
        }

        redisTemplate.expire(token, 30 * 60, TimeUnit.SECONDS); // 设置过期时间
        Shop shop = BeanUtil.map2bean(map, Shop.class);
        shop.setPassword(null);
        resultMessage.success("001", shop);
        return resultMessage;
    }

}
