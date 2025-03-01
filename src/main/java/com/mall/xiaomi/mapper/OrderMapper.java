package com.mall.xiaomi.mapper;

import com.mall.xiaomi.pojo.Order;
import com.mall.xiaomi.vo.OrderVo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    @Select("SELECT `order`.*, product.product_name AS productName, product.product_picture AS productPicture " +
            "FROM `order`, product " +
            "WHERE `order`.product_id = product.product_id AND `order`.user_id = #{userId} " +
            "ORDER BY `order`.order_time DESC")
    List<OrderVo> getOrderVoByUserId(Integer userId);
}
