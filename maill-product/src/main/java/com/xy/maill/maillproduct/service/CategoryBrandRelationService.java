package com.xy.maill.maillproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.maillproduct.entity.BrandEntity;
import com.xy.maill.maillproduct.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<CategoryBrandRelationEntity> catelogList(Long brandId);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateByBrandId(Long brandId, String name);

    void updateByCatId(Long catId, String name);

    public List<BrandEntity> getBrandsByCatId(Long catId);
}

