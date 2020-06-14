package service;

import bean.PmsBaseAttrInfo;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 19:17 2020/6/14
 * @Description:
 */
public interface AttrService {

    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);
}
