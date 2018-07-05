package com.cicd.cicd;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zhuran on 2018/7/2 0002
 */
public class JedisTest {
    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.21.238",6379);
        jedis.set("name","myimooc");
        String value = jedis.get("name");
        System.out.println(value);
        jedis.close();
    }

    @Test
    public void demo2(){
        // 获得连接池的配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(30);
        // 设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        // 获得连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.21.238",6379);
        // 通过连接池获得连接
        Jedis jedis = jedisPool.getResource();
        // 设置数据
        jedis.set("name","张三");
        // 获取数据
        String value = jedis.get("name");
        System.out.println(value);
        // 释放资源
        jedis.close();
        // 释放连接池
        jedisPool.close();
    }
}
