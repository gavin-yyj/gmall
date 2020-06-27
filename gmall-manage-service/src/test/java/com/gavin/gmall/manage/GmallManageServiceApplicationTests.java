package com.gavin.gmall.manage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import com.gavin.gmall.util.RedisUtil;

@SpringBootTest
class GmallManageServiceApplicationTests {
    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
        Jedis jedis = redisUtil.getJedis();
        System.out.println(jedis);
    }

}
