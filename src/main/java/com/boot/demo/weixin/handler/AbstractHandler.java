package com.boot.demo.weixin.handler;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liangfeihu
 */
public abstract class AbstractHandler implements WxMpMessageHandler {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
