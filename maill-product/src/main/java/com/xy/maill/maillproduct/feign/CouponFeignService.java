package com.xy.maill.maillproduct.feign;

import com.xy.maill.common.to.SkuReductionTo;
import com.xy.maill.common.to.SpuBoundTo;
import com.xy.maill.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("xymaill-nacos-coupon")  //这个是使用feign 括号里面是使用coupon项目在nacos的注册地址
public interface CouponFeignService {

    /**
     * 1、CouponFeignService.saveSpuBounds(spuBoundTo);
     *      1）、@RequestBody将这个对象转为json。
     *      2）、找到gulimall-coupon服务，给/coupon/spubounds/save发送请求。
     *          将上一步转的json放在请求体位置，发送请求；
     *      3）、对方服务收到请求。请求体里有json数据。
     *          (@RequestBody SpuBoundsEntity spuBounds)；将请求体的json转为SpuBoundsEntity；
     * 只要json数据模型是兼容的。双方服务无需使用同一个to
     * @param spuBoundTo
     * @return
     */

    //这个直接复制coupon项目的controller就行 调用上增加SpuBoundsController 类上的RequestMapping ：coupon/spubounds再加具体调用的方法
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);


    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
