package com.boot.demo.weixin.config;

import com.boot.demo.weixin.handler.*;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * wechat mp configuration
 *
 * @author liangfeihu
 */
@Slf4j
@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WechatMpProperties.class)
public class WechatMpConfiguration {
    @Autowired
    private WechatMpProperties properties;

    @Autowired
    protected LogHandler logHandler;
    @Autowired
    protected NullHandler nullHandler;
    @Autowired
    protected KfSessionHandler kfSessionHandler;
    @Autowired
    protected StoreCheckNotifyHandler storeCheckNotifyHandler;
    @Autowired
    private LocationHandler locationHandler;
    @Autowired
    private MenuHandler menuHandler;
    @Autowired
    private MsgHandler msgHandler;
    @Autowired
    private UnsubscribeHandler unsubscribeHandler;
    @Autowired
    private SubscribeHandler subscribeHandler;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;


    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        log.info("inject jedisPool param from host {}", host);

        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }

    @Bean
    public WxMpConfigStorage configStorage(JedisPool jedisPool) {
        WxMpInRedisConfigStorage configStorage = new WxMpInRedisConfigStorage(jedisPool, "ddhua.wechat.accessToken");
        configStorage.setAppId(this.properties.getAppId());
        configStorage.setSecret(this.properties.getSecret());
        configStorage.setToken(this.properties.getToken());
        configStorage.setAesKey(this.properties.getAesKey());
        return configStorage;
    }

    @Bean
    public WxMpService wxMpService(WxMpConfigStorage configStorage) {
        WxMpService wxMpService = new me.chanjar.weixin.mp.api.impl.WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }

    @Bean
    public WxMpMessageRouter router(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
                .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
                .handler(this.kfSessionHandler)
                .end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
                .handler(this.kfSessionHandler).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(MenuButtonType.CLICK).handler(this.getMenuHandler()).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(MenuButtonType.VIEW).handler(this.nullHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE).handler(this.getSubscribeHandler())
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.UNSUBSCRIBE)
                .handler(this.getUnsubscribeHandler()).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.LOCATION).handler(this.getLocationHandler())
                .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(XmlMsgType.LOCATION)
                .handler(this.getLocationHandler()).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCAN).handler(this.getScanHandler()).end();

        // 默认
        newRouter.rule().async(false).handler(this.getMsgHandler()).end();

        return newRouter;
    }

    protected MenuHandler getMenuHandler() {
        return this.menuHandler;
    }

    protected SubscribeHandler getSubscribeHandler() {
        return this.subscribeHandler;
    }

    protected UnsubscribeHandler getUnsubscribeHandler() {
        return this.unsubscribeHandler;
    }

    protected AbstractHandler getLocationHandler() {
        return this.locationHandler;
    }

    protected MsgHandler getMsgHandler() {
        return this.msgHandler;
    }

    protected AbstractHandler getScanHandler() {
        return null;
    }

}
