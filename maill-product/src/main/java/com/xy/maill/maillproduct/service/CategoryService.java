package com.xy.maill.maillproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.maillproduct.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> queryTree();

    Integer logicDelete(List<Long> ids);

    Long[] getCatPathByGroupCatId(Long catelogId);

    void updateDetail(CategoryEntity category);
}

