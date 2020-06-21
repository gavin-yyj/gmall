package service;

import bean.PmsBaseSaleAttr;
import bean.PmsProductImage;
import bean.PmsProductInfo;
import bean.PmsProductSaleAttr;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:05 2020/6/15
 * @Description:
 */
public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);
}
