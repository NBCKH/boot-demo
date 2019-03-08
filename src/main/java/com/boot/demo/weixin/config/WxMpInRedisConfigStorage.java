package com.boot.demo.weixin.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * config store in redis
 *
 * @author liangfeihu
 * @since 2018/7/27 15:45.
 */
public class WxMpInRedisConfigStorage extends me.chanjar.weixin.mp.api.WxMpInRedisConfigStorage {
    String key;

    public WxMpInRedisConfigStorage(JedisPool jedisPool, String key) {
        super(jedisPool);
        this.key = key;
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        super.updateAccessToken(accessToken, expiresInSeconds);

        Jedis jedis = this.jedisPool.getResource();
        Throwable var4 = null;

        try {
            jedis.setex(key, expiresInSeconds - 200, accessToken);
        } catch (Throwable var13) {
            var4 = var13;
            throw var13;
        } finally {
            if (jedis != null) {
                if (var4 != null) {
                    try {
                        jedis.close();
                    } catch (Throwable var12) {
                        var4.addSuppressed(var12);
                    }
                } else {
                    jedis.close();
                }
            }
        }
    }
}
