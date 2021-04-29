package com.xy.maill.maillproduct.dao;

import com.xy.maill.maillproduct.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * 
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    public List<Long> getCanSearch(@Param("attrIds") List<Long> attrIds);
}
