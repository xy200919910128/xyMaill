package com.xy.maill.maillproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.maillproduct.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateSelfAndRelation(BrandEntity brand);
}

