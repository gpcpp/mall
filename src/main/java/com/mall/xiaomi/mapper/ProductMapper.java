package com.mall.xiaomi.mapper;

import com.mall.xiaomi.pojo.Product;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Select;




import java.util.List;

public interface ProductMapper extends Mapper<Product> {

    @Select("select product_id from product")
    List<Integer> selectIds();


}
