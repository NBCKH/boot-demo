package com.boot.demo.weixin.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author liangfeihu
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class MenuHandler extends AbstractHandler {
    @Resource
    Environment environment;
//    @Autowired
//    WeixinAuthMapper weixinAuthMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {

//        try {
//            String key = wxMessage.getEventKey();
//            log.info("菜单点击按钮 key = {}", key);
//            if (WxMenuKey.CUSTOMER_SERVICE.equals(key)) {
//                String content = environment.getProperty("ddhua.newsmsg.kefu.content", String.class);
//                wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.TEXT().content(content).toUser(wxMessage.getFromUser()).build());
//            } else if (WxMenuKey.USER_FEEDBACK.equals(key)) {
//                String openId = wxMessage.getFromUser();
//                log.info("[留言板] openId = {} key = {}", openId, key);
//                if (StringUtils.isNotBlank(openId)) {
//                    WeixinAuth weixinAuth = weixinAuthMapper.getUserWeixinAuth(openId);
//                    if (weixinAuth != null && StringUtils.isNotBlank(weixinAuth.getNickname())) {
//                        String title = environment.getProperty("ddhua.newsmsg.feedback.title", String.class).replace("XXX", weixinAuth.getNickname());
//                        String description = environment.getProperty("ddhua.newsmsg.feedback.description", String.class);
//                        String feedbackUrl = environment.getProperty("ddhua.newsmsg.feedback.url", String.class);
//
//                        WxMpKefuMessage.WxArticle article = new WxMpKefuMessage.WxArticle();
//                        article.setTitle(title);
//                        article.setDescription(description);
//                        article.setUrl(feedbackUrl);
//                        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.NEWS().addArticle(article).toUser(wxMessage.getFromUser()).build());
//                    }
//                }
//
//            } else {
//                log.warn("未知的菜单点击按钮 key = {}", key);
//            }
//        } catch (WxErrorException e) {
//            log.error("菜单点击事件处理失败了", e);
//        }

        return null;
    }

}
