package com.xy.maill.maillproduct.service.impl;

import com.xy.maill.maillproduct.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.common.utils.Query;

import com.xy.maill.maillproduct.dao.CategoryDao;
import com.xy.maill.maillproduct.entity.CategoryEntity;
import com.xy.maill.maillproduct.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> queryTree() {
        //获取一级分类
        List<CategoryEntity> allCateList =  baseMapper.selectList(null);
        List<CategoryEntity> oneCateList = new ArrayList<>();
        if(allCateList!=null){
            oneCateList = allCateList.stream().filter((categoryEntity)->{
                return categoryEntity.getParentCid()==0;
            }).filter((categoryEntity)->{
                return categoryEntity.getShowStatus()>0;
            }).map((categoryEntity)->{
                 categoryEntity.setChildCategory(getChildCateList(categoryEntity,allCateList));
                 return categoryEntity;
            }).sorted((menu1,menu2)->{
                 return (menu1.getSort()==null?Integer.valueOf(0):menu1.getSort()).compareTo(menu2.getSort()==null?Integer.valueOf(0):menu2.getSort());
            }).collect(Collectors.toList());
        }

        return oneCateList;
    }

    @Override
    public Integer logicDelete(List<Long> ids) {
        return categoryDao.logicDelete(ids);
    }

    @Override
    public Long[] getCatPathByGroupCatId(Long catelogId) {
        List<Long> list = new ArrayList<>();
        return getCatByParCatId(catelogId,list);
    }

    @Override
    @Transactional
    public void updateDetail(CategoryEntity category) {
        this.updateById(category);
        if(StringUtils.isNotEmpty(category.getName())){
            categoryBrandRelationService.updateByCatId(category.getCatId(),category.getName());
        }
    }

    private Long[] getCatByParCatId(Long parId,List<Long> list) {
        list.add(parId);
        CategoryEntity category = categoryDao.selectById(parId);
        if(category!=null&&category.getParentCid()!=0){
            getCatByParCatId(category.getParentCid(),list);
        }
        Collections.reverse(list);
        return list.toArray(new Long[list.size()]);
    }


    /**
     * 根据父类id查询子类类别
     */
    private List<CategoryEntity> getChildCateList (CategoryEntity categoryEntity,List<CategoryEntity> allCateList) {
        List<CategoryEntity> childlList =  allCateList.stream().filter((menu)->{
            return menu.getParentCid() == categoryEntity.getCatId();
        }).filter((menu)->{
            return menu.getShowStatus()>0;
        }).map((menu)->{
            menu.setChildCategory(getChildCateList(menu,allCateList));
            return menu;
        }).sorted((m1,m2)->{
            return (m1.getSort()==null?Integer.valueOf(0):m1.getSort()).compareTo(m2.getSort()==null?Integer.valueOf(0):m2.getSort());
        }).collect(Collectors.toList());

        return childlList;
    }

}