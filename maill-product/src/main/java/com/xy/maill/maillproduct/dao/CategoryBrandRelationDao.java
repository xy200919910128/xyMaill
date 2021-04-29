package com.xy.maill.maillproduct.dao;

import com.xy.maill.maillproduct.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌分类关联
 * 
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    void updateByCatId(@Param("catId") Long catId,@Param("name") String name);
}
