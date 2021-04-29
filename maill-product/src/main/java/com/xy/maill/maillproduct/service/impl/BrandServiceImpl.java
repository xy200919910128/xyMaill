package com.xy.maill.maillproduct.service.impl;

import com.xy.maill.maillproduct.entity.AttrGroupEntity;
import com.xy.maill.maillproduct.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.common.utils.Query;

import com.xy.maill.maillproduct.dao.BrandDao;
import com.xy.maill.maillproduct.entity.BrandEntity;
import com.xy.maill.maillproduct.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<BrandEntity>();
        if(StringUtils.isNotEmpty(key)){
            wrapper.and((obj)->{
                obj.eq("brand_id",key).or().like("name",key);
            });
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper);
        return new PageUtils(page);
    }

    @Override
    public void updateSelfAndRelation(BrandEntity brand) {
        this.updateById(brand);
        if(StringUtils.isNotEmpty(brand.getName())){
            categoryBrandRelationService.updateByBrandId(brand.getBrandId(),brand.getName());
            //todo
        }
    }

}