package com.boot.demo.weixin.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liangfeihu
 */
@SuppressWarnings("ALL")
@Component
public class UnsubscribeHandler extends AbstractHandler {
//    @Autowired
//    WeixinAuthMapper weixinAuthMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
//        String openId = wxMessage.getFromUser();
//        this.logger.info("用户取消关注 OPENID: " + openId);
//        WeixinAuth oldWeixinAuth = weixinAuthMapper.getUserWeixinAuth(openId);
//        if (oldWeixinAuth == null) {
//            this.logger.warn("未知用户取消关注 openId {}", openId);
//        } else {
//            //oldWeixinAuth.setDeleted(new Date());
//            oldWeixinAuth.setIsSubscribe(false);
//            if(oldWeixinAuth.getUserId() != null){
//                oldWeixinAuth.setRemark("取消关注_" + oldWeixinAuth.getUserId());
//            }
//            oldWeixinAuth.setUserId(null);
//            oldWeixinAuth.setUpdated(new Date());
//            //oldWeixinAuth.setVersion(oldWeixinAuth.getVersion() + 1);
//            weixinAuthMapper.update(oldWeixinAuth);
//        }
        return null;
    }

}
