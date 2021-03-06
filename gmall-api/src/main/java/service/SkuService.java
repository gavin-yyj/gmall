package service;

import bean.PmsSkuInfo;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 19:24 2020/6/21
 * @Description:
 */
public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId,String ip);

    List<PmsSkuInfo> getSkuSaleAtrValueListBySpu(String productId);

    List<PmsSkuInfo> getAllSku();
}
