package service;

import bean.PmsBaseSaleAttr;
import bean.PmsProductInfo;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:05 2020/6/15
 * @Description:
 */
public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
