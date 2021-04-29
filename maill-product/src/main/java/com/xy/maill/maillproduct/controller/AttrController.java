package com.xy.maill.maillproduct.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.xy.maill.maillproduct.entity.ProductAttrValueEntity;
import com.xy.maill.maillproduct.service.ProductAttrValueService;
import com.xy.maill.maillproduct.vo.AttrResponseVoEntity;
import com.xy.maill.maillproduct.vo.AttrVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xy.maill.maillproduct.entity.AttrEntity;
import com.xy.maill.maillproduct.service.AttrService;
import com.xy.maill.common.utils.PageUtils;
import com.xy.maill.common.utils.R;



/**
 * 商品属性
 *
 * @author xy
 * @email xy@gmail.com
 * @date 2020-08-10 10:37:38
 */
@RestController
@RequestMapping("maillproduct/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    @Autowired
    ProductAttrValueService productAttrValueService;

    ///product/attr/info/{attrId}

    // /product/attr/base/listforspu/{spuId}
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId){
        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrlistforspu(spuId);
        return R.ok().put("data",entities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("maillproduct:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 基本列表查询
     */
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseList(@RequestParam Map<String, Object> params,@PathVariable("catelogId") Long catelogId,@PathVariable("attrType") String attrType) {
        PageUtils page = attrService.queryBaseListPage(params,catelogId,attrType);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("maillproduct:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
        AttrResponseVoEntity attr = attrService.getByIdAndGroupAndCatPath(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("maillproduct:attr:save")
    public R save(@RequestBody AttrVoEntity attr){
		attrService.saveAttr(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("maillproduct:attr:update")
    public R update(@RequestBody AttrVoEntity attrVoEntity){
		attrService.updateAllInfo(attrVoEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("maillproduct:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

    ///product/attr/update/{spuId}
    @PostMapping("/update/{spuId}")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){
        productAttrValueService.updateSpuAttr(spuId,entities);
        return R.ok();
    }

}
