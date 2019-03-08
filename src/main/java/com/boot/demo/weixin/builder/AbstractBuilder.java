package com.boot.demo.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liangfeihu
 * @since 2018/3/23 20:54.
 */
public abstract class AbstractBuilder {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service);

}
