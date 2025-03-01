package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.User;
import com.mall.xiaomi.service.UserService;
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
@RequestMapping("/user")
@Api(value = "用户管理",tags = "获取用户接口")
public class UserController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResultMessage login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        user = userService.login(user);
        // 添加cookie，设置唯一认证
        String encode = MD5Util.MD5Encode(user.getUsername() + user.getPassword(), "UTF-8");
        // 进行加盐
        encode += "|" + user.getUserId() + "|" + user.getUsername() + "|";
        CookieUtil.setCookie(request, response, "XM_TOKEN", encode, 1800);
        // 将encode放入redis中，用于认证
        try {
            redisTemplate.opsForHash().putAll(encode, BeanUtil.bean2map(user));
            redisTemplate.expire(encode, 30 * 60, TimeUnit.SECONDS); // 设置过期时间
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将密码设为null,返回给前端
        user.setPassword(null);
        resultMessage.success("001", "登录成功", user);
        return resultMessage;
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public ResultMessage register(@RequestBody User user) {
        userService.register(user);
        resultMessage.success("001", "注册成功");
        return resultMessage;
    }

    @ApiOperation(value = "用户名是否可用")
    @GetMapping("/username/{username}")
    public ResultMessage username(@PathVariable String username) {
        userService.isUserName(username);
        resultMessage.success("001", "可注册");
        return resultMessage;
    }

    @ApiOperation(value = "获取用户信息")
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
        User user = BeanUtil.map2bean(map, User.class);
        user.setPassword(null);
        resultMessage.success("001", user);
        return resultMessage;
    }

}
