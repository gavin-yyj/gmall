package service;

import bean.PmsSearchParam;
import bean.PmsSearchSkuInfo;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 20:51 2020/6/27
 * @Description:
 */
public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);
}
