package com.boot.demo.weixin.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liangfeihu
 */
@SuppressWarnings("ALL")
@Component
public class SubscribeHandler extends AbstractHandler {

//    @Autowired
//    WeixinAuthMapper weixinAuthMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

//        // 获取微信用户基本信息
//        WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);
//        String openId = wxMessage.getFromUser();
//        String nickname = "";
//        if (userWxInfo != null) {
//            nickname = userWxInfo.getNickname();
//            WeixinAuth oldWeixinAuth = weixinAuthMapper.getUserWeixinAuth(openId);
//            if (oldWeixinAuth == null) {
//                WeixinAuth weixinAuth = new WeixinAuth();
//                weixinAuth.setIsSubscribe(true);
//                weixinAuth.setCity(userWxInfo.getCity());
//                weixinAuth.setCountry(userWxInfo.getCountry());
//                weixinAuth.setHeadimgUrl(userWxInfo.getHeadImgUrl());
//                weixinAuth.setLanguage(userWxInfo.getLanguage());
//                try {
//                    weixinAuth.setNickname(StringUtils.isEmpty(userWxInfo.getNickname()) ? "" : FileUtils.emojiConvert(userWxInfo.getNickname()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                weixinAuth.setOpenId(openId);
//                weixinAuth.setProvince(userWxInfo.getProvince());
//                weixinAuth.setSex(userWxInfo.getSex());
//                weixinAuth.setSubscribeTime(new Date());
//                weixinAuth.setRemark(userWxInfo.getRemark());
//                weixinAuth.setCreated(new Date());
//                weixinAuth.setUpdated(new Date());
//                weixinAuth.setVersion(0);
//                weixinAuthMapper.insert(weixinAuth);
//            } else {
//                oldWeixinAuth.setDeleted(null);
//                oldWeixinAuth.setIsSubscribe(true);
//                oldWeixinAuth.setCity(userWxInfo.getCity());
//                oldWeixinAuth.setCountry(userWxInfo.getCountry());
//                oldWeixinAuth.setHeadimgUrl(userWxInfo.getHeadImgUrl());
//                oldWeixinAuth.setLanguage(userWxInfo.getLanguage());
//                try {
//                    oldWeixinAuth.setNickname(StringUtils.isEmpty(userWxInfo.getNickname()) ? "" : FileUtils.emojiConvert(userWxInfo.getNickname()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                oldWeixinAuth.setOpenId(openId);
//                oldWeixinAuth.setProvince(userWxInfo.getProvince());
//                oldWeixinAuth.setSex(userWxInfo.getSex());
//                oldWeixinAuth.setSubscribeTime(new Date());
//                String oldRemark = oldWeixinAuth.getRemark();
//                String remark = userWxInfo.getRemark();
//                Long userId = null;
//                try {
//                    if (StringUtils.isNotEmpty(oldRemark) && oldRemark.contains("取消关注_")) {
//                        userId = Long.valueOf(oldRemark.substring(oldRemark.indexOf("_") + 1));
//                        oldWeixinAuth.setUserId(userId);
//                        remark = "重新关注_" + userId;
//                    }
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//                oldWeixinAuth.setRemark(remark);
//                weixinAuthMapper.update(oldWeixinAuth);
//            }
//        }
//
//        WxMpXmlOutMessage responseResult = null;
//        try {
//            responseResult = handleSpecial(wxMessage);
//        } catch (Exception e) {
//            this.logger.error(e.getMessage(), e);
//        }
//
//        if (responseResult != null) {
//            return responseResult;
//        }
//
//        try {
//            return new TextBuilder().build("感谢关注点点花", wxMessage, weixinService);
//        } catch (Exception e) {
//            this.logger.error(e.getMessage(), e);
//        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}
