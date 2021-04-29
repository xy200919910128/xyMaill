package com.xy.maill.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 13:15:02
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

