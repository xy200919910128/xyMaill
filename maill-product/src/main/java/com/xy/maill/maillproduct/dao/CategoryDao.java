package com.xy.maill.maillproduct.dao;

import com.xy.maill.maillproduct.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品三级分类
 * 
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {


    public Integer  logicDelete(@Param("ids") List<Long> ids);
}
