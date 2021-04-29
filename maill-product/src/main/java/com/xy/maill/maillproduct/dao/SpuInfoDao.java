package com.xy.maill.maillproduct.dao;

import com.xy.maill.common.constant.ProductConstant;
import com.xy.maill.maillproduct.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 * 
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:37
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    void updateSpuStatus(@Param("spuId") Long spuId,@Param("status") Integer status);
}
