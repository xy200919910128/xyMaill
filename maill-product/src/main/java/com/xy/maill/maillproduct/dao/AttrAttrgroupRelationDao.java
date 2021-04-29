package com.xy.maill.maillproduct.dao;

import com.xy.maill.maillproduct.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.maill.maillproduct.vo.AttrIdVoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 * 
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void batchDelete(@Param("attrIdVoEntityList") List<AttrIdVoEntity> attrIdVoEntityList);
}
