package com.xy.maill.maillproduct.web;

import com.xy.maill.maillproduct.entity.CategoryEntity;
import com.xy.maill.maillproduct.service.CategoryService;
import com.xy.maill.maillproduct.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private  CategoryService categoryService;

    @GetMapping({"/","/index.html"})
    public String indexPage(Model model) {
        //会按照配置 返回 会找classpath:/templates/下的index.html
        List<CategoryEntity> categoryEntityList = categoryService.getCatLevel1();
        model.addAttribute("categoryEntitys",categoryEntityList);
        return "index";
    }

    @GetMapping("/index/json/catalog")
    @ResponseBody
    public Map<String, List<Catelog2Vo>> getCatalogJson(){
        return categoryService.getCatalogJson();
    }
}
