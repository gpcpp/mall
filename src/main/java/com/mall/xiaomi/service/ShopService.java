package com.mall.xiaomi.service;

import com.mall.xiaomi.exception.ExceptionEnum;
import com.mall.xiaomi.exception.XmException;
import com.mall.xiaomi.mapper.ShopMapper;
import com.mall.xiaomi.pojo.Shop;
import com.mall.xiaomi.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ShopMapper shopMapper;

    public Shop login(Shop shop) {
        shop.setPassword(MD5Util.MD5Encode(shop.getPassword() + "", "UTF-8"));
        Shop one = shopMapper.selectOne(shop);
        if (one == null) {
            throw new XmException(ExceptionEnum.GET_USER_NOT_FOUND);
        }
        return one;
    }

    public void register(Shop shop) {
        Shop one = new Shop();
        one.setUsername(shop.getUsername());
        // 先去看看用户名是否重复
        if (shopMapper.selectCount(one) == 1) {
            // 用户名已存在
            throw new XmException(ExceptionEnum.SAVE_USER_REUSE);
        }
        // 使用md5对密码进行加密
        shop.setPassword(MD5Util.MD5Encode(shop.getPassword() + "", "UTF-8"));
        // 存入数据库
        try {
            shopMapper.insert(shop);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.SAVE_USER_ERROR);
        }
    }

    public void isUserName(String username) {
        Shop one = new Shop();
        one.setUsername(username);
        // 先去看看用户名是否重复
        if (shopMapper.selectCount(one) == 1) {
            // 用户名已存在
            throw new XmException(ExceptionEnum.SAVE_USER_REUSE);
        }
    }
}
