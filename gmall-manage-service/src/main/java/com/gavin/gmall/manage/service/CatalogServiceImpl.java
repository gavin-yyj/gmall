package com.gavin.gmall.manage.service;

import bean.PmsBaseCatalog1;
import bean.PmsBaseCatalog2;
import bean.PmsBaseCatalog3;
import com.alibaba.dubbo.config.annotation.Service;
import com.gavin.gmall.manage.mapper.PmsCatalog1Mapper;
import com.gavin.gmall.manage.mapper.PmsCatalog2Mapper;
import com.gavin.gmall.manage.mapper.PmsCatalog3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.CatalogService;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:05 2020/6/14
 * @Description:
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsCatalog1Mapper pmsCatalog1Mapper;
    @Autowired
    PmsCatalog2Mapper pmsCatalog2Mapper;
    @Autowired
    PmsCatalog3Mapper pmsCatalog3Mapper;
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsCatalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        return pmsCatalog2Mapper.select(pmsBaseCatalog2);
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        return pmsCatalog3Mapper.select(pmsBaseCatalog3);
    }
}
