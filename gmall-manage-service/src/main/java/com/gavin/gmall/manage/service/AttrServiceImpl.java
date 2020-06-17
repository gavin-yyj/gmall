package com.gavin.gmall.manage.service;

import bean.PmsBaseAttrInfo;
import bean.PmsBaseAttrValue;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gavin.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.gavin.gmall.manage.mapper.PmsBaseAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.AttrService;
import tk.mybatis.mapper.entity.Example;

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
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> attrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        return attrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String id = pmsBaseAttrInfo.getId();
        //如果id为空，则保存
        if(StringUtils.isBlank(id)){
            /**保存属性*/
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);

            /**保存属性值*/
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                //保存商品属性值
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }else{
            //id非空，说明是修改操作
            /**属性修改*/
            Example example = new Example(PmsBaseAttrInfo.class);
            //根据id来修改属性
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            //根据example的条件来修改
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);

            /*修改属性值**/
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();

            /**按照属性ID删除所有属性值*/
            PmsBaseAttrValue attrValue = new PmsBaseAttrValue();
            attrValue.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(attrValue);

            /**删除后，将新的属性值插入*/
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }
        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        List<PmsBaseAttrValue> attrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
        return attrValues;
    }
}
