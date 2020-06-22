package com.gavin.gmall.item.controller;

import bean.PmsProductSaleAttr;
import bean.PmsSkuInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import service.SkuService;
import service.SpuService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 11:06 2020/6/22
 * @Description:
 */
@Controller
public class ItemController {
    @Reference
    SkuService skuService;
    @Reference
    SpuService spuService;

    @RequestMapping("index")
    public String index(ModelMap modelMap){
        modelMap.put("hello","thymeleaf又见面了！");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("yangyujie"+i);
        }
        modelMap.put("list",list);

        modelMap.put("check","0");
        return "index";
    }

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId,ModelMap map){
        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId);
        //sku对象
        map.put("skuInfo",pmsSkuInfo);

        //销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());
        map.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);

        return "item";
    }
}
