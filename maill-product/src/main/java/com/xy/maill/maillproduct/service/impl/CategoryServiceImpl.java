package com.xy.maill.maillproduct.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xy.maill.maillproduct.service.CategoryBrandRelationService;
import com.xy.maill.maillproduct.vo.Catelog2Vo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
import org.springframework.util.CollectionUtils;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    @Override
    public List<CategoryEntity> getCatLevel1() {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_cid","0");
        List<CategoryEntity> allCateList =  baseMapper.selectList(queryWrapper);
        return allCateList;
    }

    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList, Long parent_cid) {
        selectList = selectList.stream().filter(item -> item.getParentCid() == parent_cid).collect(Collectors.toList());
        return selectList;
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String catJson = stringStringValueOperations.get("catJson");
        if(StringUtils.isEmpty(catJson)){
            Map<String, List<Catelog2Vo>> map = getCatalogJsonFromDb();
            //将对象转为jsonStr存入缓存（序列化）
            stringStringValueOperations.set("catJson", JSONObject.toJSONString(map));
        }
        //可以将jsonStr转为任意对象的方法（反序列化）
        return JSONObject.parseObject(catJson,new TypeReference<Map<String, List<Catelog2Vo>> >(){});
    }

    //从数据库查询并封装分类数据
    private Map<String, List<Catelog2Vo>> getCatalogJsonFromDb() {
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        List<CategoryEntity> level1Categories = getParent_cid(selectList, 0L);

        Map<String, List<Catelog2Vo>> parentCid = level1Categories.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            List<Catelog2Vo> catelog2VOS = null;
            if (!CollectionUtils.isEmpty(categoryEntities)) {
                catelog2VOS = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2VO = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    List<CategoryEntity> level3Catalog = getParent_cid(selectList, l2.getCatId());
                    if (!CollectionUtils.isEmpty(level3Catalog)) {
                        List<Catelog2Vo.Catelog3Vo> collect = level3Catalog.stream().map(l3 -> {
                            Catelog2Vo.Catelog3Vo catelog3VO = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3VO;
                        }).collect(Collectors.toList());
                        catelog2VO.setCatalog3List(collect);
                    }
                    return catelog2VO;
                }).collect(Collectors.toList());
            }
            return catelog2VOS;
        }));
        return parentCid;
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