package com.gavin.gmall.manage.service;

import bean.PmsSkuAttrValue;
import bean.PmsSkuImage;
import bean.PmsSkuInfo;
import bean.PmsSkuSaleAttrValue;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.gavin.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.gavin.gmall.manage.mapper.PmsSkuImageMapper;
import com.gavin.gmall.manage.mapper.PmsSkuInfoMapper;
import com.gavin.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import service.SkuService;
import com.gavin.gmall.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 19:25 2020/6/21
 * @Description:
 */
@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //插入SkuInfo
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();

        //插入sku平台属性关系
        List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for(PmsSkuAttrValue pmsSkuAttrValue:pmsSkuAttrValueList){
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        //插入销售属性关系
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for(PmsSkuSaleAttrValue pmsSkuSaleAttrValue:skuSaleAttrValueList){
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

        //插入图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for(PmsSkuImage pmsSKuImage:skuImageList){
            pmsSKuImage.setSkuId(skuId);
            pmsSkuImageMapper.insertSelective(pmsSKuImage);
        }

    }
    public PmsSkuInfo getSkuByIdFromDb(String skuId) {
        //获取sku商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        //获取sku图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId,String ip) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        // 链接缓存
        Jedis jedis = redisUtil.getJedis();
        // 查询缓存
        String skuKey = "sku:"+skuId+":info";
        String skuJson = jedis.get(skuKey);

        //if(skuJson!=null&&!skuJson.equals(""))
        if(StringUtils.isNotEmpty(skuJson)){
            pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
            System.out.println("ip:"+ip+" "+Thread.currentThread().getName()+"从缓存拿取数据");
        }else{
            // 如果缓存中没有，查询mysql
            System.out.println("ip:"+ip+" "+Thread.currentThread().getName()+"申请从数据库中拿取数据 " +"suk:"+skuId+":lock");
            // 设置分布式锁
            String token = UUID.randomUUID().toString();
            String OK = jedis.set("sku:" + skuId + ":lock", token, "nx", "px", 10*1000);
            if(StringUtils.isNotEmpty(OK)&&OK.equals("OK")){
                // 设置成功，有权在10秒的过期时间内访问数据库
                System.out.println("ip:"+ip+" "+Thread.currentThread().getName()+"有权在10s的过期时间内访问数据库: " +"suk:"+skuId+":lock");
                pmsSkuInfo =  getSkuByIdFromDb(skuId);

                try{
                    Thread.sleep(1000*5);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                if(pmsSkuInfo!=null){
                    // mysql查询结果存入redis
                    jedis.set("sku:"+skuId+":info",JSON.toJSONString(pmsSkuInfo));
                }else{
                    // 数据库中不存在该sku
                    // 为了防止缓存穿透将null或者空字符串值设置给redis
                    // 将空字符串的过期时间设置为3分钟
                    jedis.setex("sku:"+skuId+":info",60*3,JSON.toJSONString(""));
                }

                //在访问mysql后，将mysql的分布锁释放
                System.out.println("ip:"+ip+" "+Thread.currentThread().getName()+"使用完毕，将锁归还："+"sku"+skuId+":lock");
                String lockToken = jedis.get("sku:"+skuId+":lock");
                if(StringUtils.isNotEmpty(lockToken) && lockToken.equals(token)){
                    jedis.del("sku:"+skuId+":lock");
                }
            }else{
                // 设置失败，自旋（该线程在睡眠几秒后，重新尝试访问本方法）
                System.out.println("ip:"+ip+" "+Thread.currentThread().getName()+"没有拿到锁，开始自旋");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return getSkuById(skuId,ip);
            }
        }
        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAtrValueListBySpu(String productId) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfos;
    }

    @Override
    public List<PmsSkuInfo> getAllSku() {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectAll();

        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            String skuId = pmsSkuInfo.getId();

            PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue();
            pmsSkuAttrValue.setSkuId(skuId);
            List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueMapper.select(pmsSkuAttrValue);
            pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValues);
        }
        return pmsSkuInfos;
    }
}
