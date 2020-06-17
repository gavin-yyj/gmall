package com.gavin.gmall.manage.service;

import bean.PmsBaseSaleAttr;
import bean.PmsProductInfo;
import bean.PmsProductSaleAttr;
import com.alibaba.dubbo.config.annotation.Service;
import com.gavin.gmall.manage.mapper.PmsProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.gavin.gmall.manage.mapper.PmsProductSaleAttrMapper;
import service.SpuService;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:06 2020/6/15
 * @Description:
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        List<PmsProductInfo> infos = pmsProductInfoMapper.select(pmsProductInfo);
        return infos;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsProductSaleAttrMapper.selectAll();
        return pmsBaseSaleAttrs;
    }
}
