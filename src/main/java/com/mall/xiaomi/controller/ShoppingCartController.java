package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.ShoppingCart;
import com.mall.xiaomi.service.ShoppingCartService;
import com.mall.xiaomi.util.ResultMessage;
import com.mall.xiaomi.vo.CartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Api(value = "购物车管理", tags = "获取购物车接口")
public class ShoppingCartController{

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private ShoppingCartService cartService;

    @ApiOperation(value = "获取购物车")
    @GetMapping("/user/{userId}")
    public ResultMessage cart(@PathVariable String userId) {
        List<CartVo> carts = cartService.getCartByUserId(userId);
        resultMessage.success("001", carts);
        return resultMessage;
    }

    @ApiOperation(value = "添加购物车")
    @PostMapping("/product/user/{productId}/{userId}")
    public ResultMessage cart(@PathVariable String productId, @PathVariable String userId) {
        CartVo cartVo = cartService.addCart(productId, userId);
        if (cartVo != null) {
            resultMessage.success("001", "添加购物车成功", cartVo);
        }else {
            resultMessage.success("002", "该商品已经在购物车，数量+1");
        }
        return resultMessage;
    }
    @ApiOperation(value = "更新购物车")
    @PutMapping("/user/num/{cartId}/{userId}/{num}")
    public ResultMessage cart(@PathVariable String cartId, @PathVariable String userId, @PathVariable String num) {
        cartService.updateCartNum(cartId, userId, num);
        resultMessage.success("001", "更新成功");
        return resultMessage;
    }
    @ApiOperation(value = "删除购物车")
    @DeleteMapping("/user/{cartId}/{userId}")
    public ResultMessage deleteCart(@PathVariable String cartId, @PathVariable String userId) {
        cartService.deleteCart(cartId, userId);
        resultMessage.success("001", "删除成功");
        return resultMessage;
    }
}
