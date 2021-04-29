package com.xy.maill.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:59:40
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
