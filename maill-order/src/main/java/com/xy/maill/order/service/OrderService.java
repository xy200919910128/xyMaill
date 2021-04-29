package com.xy.maill.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 14:23:24
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

