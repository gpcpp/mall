package com.mall.xiaomi.controller;

import com.github.pagehelper.PageInfo;
import com.mall.xiaomi.pojo.Product;
import com.mall.xiaomi.pojo.SeckillProduct;
import com.mall.xiaomi.service.ProductService;
import com.mall.xiaomi.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
@Api(value = "商品管理", tags = "获取商品接口")
public class ProductController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private ProductService productService;
    @ApiOperation(value = "根据商品id获取商品")
    @GetMapping("/category/limit/{categoryId}")
    public ResultMessage getProductByCategoryId(@PathVariable Integer categoryId) {
        List<Product> list = productService.getProductByCategoryId(categoryId);
        resultMessage.success("001", list);
        return resultMessage;

    }
    @ApiOperation(value = "获取热销商品")
    @GetMapping("/category/hot")
    public ResultMessage getHotProduct() {
        List<Product> list = productService.getHotProduct();
        resultMessage.success("001", list);
        return resultMessage;

    }
    @ApiOperation(value = "根据商品id获取商品")
    @GetMapping("/{productId}")
    public ResultMessage getProduct(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        resultMessage.success("001", product);
        return resultMessage;
    }
    @ApiOperation(value = "根据商品类别分页获取商品")
    @GetMapping("/page/{currentPage}/{pageSize}/{categoryId}")
    public Map<String, Object> getProductByPage(@PathVariable String currentPage, @PathVariable String pageSize, @PathVariable String categoryId) {
        PageInfo<Product> pageInfo = productService.getProductByPage(currentPage, pageSize, categoryId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "001");
        map.put("data", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @ApiOperation(value = "添加商品")
    @PostMapping("")
    public ResultMessage addSeckillProduct(@RequestBody Product product) {
        productService.addProduct(product);
        resultMessage.success("001", "商品添加成功");
        return resultMessage;
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("/product/{productId}")
    public ResultMessage deleteCollect(@PathVariable String productId) {
        productService.deleteProduct(productId);
        resultMessage.success("001", "删除商品成功");
        return resultMessage;
    }

}
