package com.gavin.gmall.manage.service;

import bean.PmsBaseAttrInfo;
import com.alibaba.dubbo.config.annotation.Service;
import com.gavin.gmall.manage.mapper.PmsBaseAttrInfoMaper;
import org.springframework.beans.factory.annotation.Autowired;
import service.AttrService;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 19:19 2020/6/14
 * @Description:
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMaper pmsBaseAttrInfoMaper;
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> attrInfos = pmsBaseAttrInfoMaper.select(pmsBaseAttrInfo);
        return attrInfos;
    }
}
