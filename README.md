# 秒杀商城项目

## 项目简介

秒杀商城是一个基于 Spring Boot 和 Vue 的全栈应用，旨在为用户提供高并发的秒杀商品购买体验。系统支持秒杀商品管理、用户登录、订单生成、支付处理等功能，致力于打造一个流畅、高效、稳定的秒杀购物平台。

## 功能模块

1. **用户管理**
   - 用户注册/登录
   - 用户信息管理


2. **商品管理**
   - 商品上架/下架
   - 秒杀商品管理（设置秒杀时间、库存等）
   - 商品详情管理

3. **秒杀活动**
   - 秒杀活动创建与管理
   - 秒杀商品库存控制
   - 秒杀抢购功能（高并发处理）

4. **订单管理**
   - 生成秒杀订单
   - 订单状态管理（未支付、已支付、已发货等）

5. **支付模块**
   - 支付订单（支持多种支付方式，如支付宝、微信支付等）


## 技术栈

- **后端**: Spring Boot, Spring Security, Redis（缓存与分布式锁），MyBatis
- **数据库**: MySQL
- **消息队列**: RabbitMQ

