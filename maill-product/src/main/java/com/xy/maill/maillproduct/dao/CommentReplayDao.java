package com.xy.maill.maillproduct.dao;

import com.xy.maill.maillproduct.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
