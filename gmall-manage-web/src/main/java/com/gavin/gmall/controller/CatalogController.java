package com.gavin.gmall.controller;

import bean.PmsBaseCatalog1;
import bean.PmsBaseCatalog2;
import bean.PmsBaseCatalog3;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CatalogService;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 14:27 2020/6/14
 * @Description:
 */
@Controller
@CrossOrigin
public class CatalogController {
    @Reference
    CatalogService catalogService;
    @RequestMapping("/getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> catalog1s = catalogService.getCatalog1();
        return catalog1s;
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){
        List<PmsBaseCatalog2> catalog2s = catalogService.getCatalog2(catalog1Id);
        return catalog2s;
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){
        List<PmsBaseCatalog3> catalog3s = catalogService.getCatalog3(catalog2Id);
        return catalog3s;
    }
}
