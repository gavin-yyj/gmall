package com.gavin.gmall.controller;

import bean.PmsBaseSaleAttr;
import bean.PmsProductInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.SpuService;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:01 2020/6/15
 * @Description:
 */
@Controller
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo>spuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = spuService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        return "success";
    }

    /**图片上传*/
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile){
        //将图片或音视频上传到分布式的文件存储系统

        //将图片的存储路径返回给页面
        String url = "https://m.360buyimg.com/babel/jfs/t5137/20/1794970752/352145/d56e4e94/591417dcN4fe5ef33.jpg";
        return url;
    }
}
