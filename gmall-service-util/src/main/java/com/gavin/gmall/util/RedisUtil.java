package com.gavin.gmall.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 14:32 2020/6/23
 * @Description:
 */

public class RedisUtil {
    private JedisPool jedisPool;

    public void initPool(String host,int port,int database){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 设置最大连接数,默认18个
        jedisPoolConfig.setMaxTotal(200);
        // 设置最大空闲连接数，默认为8
        jedisPoolConfig.setMaxIdle(30);
        // 当池中的资源耗尽时是否进行阻塞,设置false直接报错,true表示会一直等待，直到有可用资源
        jedisPoolConfig.setBlockWhenExhausted(true);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),
        // 如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(10*1000);
        // 从池中获取连接时是否测试连接的有效性,默认false
        jedisPoolConfig.setTestOnBorrow(true);

        jedisPool = new JedisPool(jedisPoolConfig,host,port,20*10000);

    }

    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
}
