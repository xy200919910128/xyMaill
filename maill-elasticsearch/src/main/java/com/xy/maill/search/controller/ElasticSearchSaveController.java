package com.xy.maill.search.controller;

import com.xy.maill.common.exception.BizCode;
import com.xy.maill.common.to.es.SkuEsModel;
import com.xy.maill.common.utils.R;
import com.xy.maill.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/search/save")
@RestController
@Slf4j
public class ElasticSearchSaveController {
    @Autowired
    ProductSaveService productSaveService;
    /**
     * 上架商品
     * @param skuEsModels
     * @return
     */
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels){
        boolean b = false;
        try {
            b = productSaveService.productStatusUp(skuEsModels);
        }catch (Exception e){
            log.error("ElasticSaveController.productStatusUp商品上架错误：{}",e);
            return R.error(BizCode.PRODUCT_UP_EXCEPTION.getCode(),BizCode.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if(!b){return R.ok();}
        else {
            return R.error(BizCode.PRODUCT_UP_EXCEPTION.getCode(),BizCode.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }

}
