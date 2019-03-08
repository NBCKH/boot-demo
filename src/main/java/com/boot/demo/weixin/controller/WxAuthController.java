package com.boot.demo.weixin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信网页授权
 *
 */
@Slf4j
@Controller
@RequestMapping("/weixin")
@SuppressWarnings("ALL")
public class WxAuthController {
//    @Resource
//    Environment environment;
//
//    @Autowired
//    WeixinAuthMapper weixinAuthMapper;
//    @Autowired
//    UserMapper userMapper;
//    @Resource
//    UserTokenServiceImpl userTokenService;
//    @Autowired
//    LoanPlatformConfigMapper loanPlatformConfigMapper;
//    @Autowired
//    UserApplyLogMapper userApplyLogMapper;
//    @Autowired
//    UserLoginLogMapper userLoginLogMapper;
//    @Autowired
//    private WxMpService wxService;
//    @Resource
//    UserServiceImpl userService;
//    @Autowired
//    MarsRiskServiceImpl marsRiskService;
//    @Autowired
//    UserMarsRiskLogMapper userMarsRiskLogMapper;
//
//    @RequestMapping("/oauth2")
//    public String weixinAuth(HttpServletResponse response,
//                             HttpServletRequest request,
//                             @RequestParam(value = "code") String code,
//                             @RequestParam(value = "state") String state,
//                             @RequestParam(value = "returnUrl", required = false) String returnUrl,
//                             @RequestParam(value = "track", required = false) String track,
//                             @RequestParam(value = "series", required = false) String series,
//                             @RequestParam(value = "recommendType", required = false) Integer recommendType,
//                             @RequestParam(value = "webId", required = false) String webId) throws Exception {
//        String domain = environment.getProperty("ddhua.h5.domain", String.class);
//        String redirectUrl = "redirect:" + domain;
//        try {
//            log.info("[网页授权01] code={} state={} returnUrl={}", code, state, returnUrl);
//            log.info("[网页授权02] webId={} series={} track={}", webId, series, track);
//            String openId = "";
//            try {
//                WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
//                openId = wxMpOAuth2AccessToken.getOpenId();
//            } catch (WxErrorException e) {
//                log.error("[网页授权]获取openId失败了 state = {} {}", state, e);
//            }
//
//            if (StringUtils.isEmpty(openId)) {
//                return redirectUrl + "/#/clickAgain";
//            }
//            log.info("[网页授权]series={} openId = {} recommendType={}", series, openId, recommendType);
//
//            returnUrl = StringUtils.isBlank(returnUrl) ? "" : returnUrl;
//            track = StringUtils.isBlank(track) ? "" : track;
//            series = StringUtils.isBlank(series) ? "" : series;
//
//            WeixinAuth weixinAuth = weixinAuthMapper.getUserWeixinAuth(openId);
//
//            // 1、无WeixinAuth则新增weixin用户
//            if (weixinAuth == null) {
//                weixinAuth = new WeixinAuth();
//                weixinAuth.setOpenId(openId);
//                weixinAuth.setRemark(series);
//                weixinAuthMapper.insert(weixinAuth);
//                log.info("[网页授权1]series={} insert weixin user openid={}", series, openId);
//            }
//
//            // 2、openId与userId已绑定则进行登录操作
//            if (weixinAuth != null && weixinAuth.getUserId() != null && !SecurityUtils.getSubject().isAuthenticated()) {
//                log.info("[网页授权2]series={} start to login: userId={} webId={}", series, weixinAuth.getUserId(), webId);
//                // 直接登录处理
//                Map<String, String> map = handleLogin(weixinAuth.getUserId(), response);
//                // 插入用户申请日志
//                insertUserApplyLog(weixinAuth.getUserId(), track, series, webId, recommendType);
//                // 插入登陆日志
//                insertUserLoginLog(weixinAuth.getUserId(), openId, series, map, track, webId, request);
//
//                try {
//                    Boolean jumpHomeFlag = Boolean.FALSE;
//                    // 对接第三方平台处理
//                    if (StringUtils.isNotBlank(track)) {
//                        String platformId = track.substring(track.lastIndexOf("-") + 1);
//                        // 信用金卡
//                        if ("1053".equals(platformId)) {
//                            userService.submitPhoneToXYJK(map.get("phone"), weixinAuth.getUserId(), Long.valueOf(platformId));
//                            jumpHomeFlag = userService.judgeUserNeedJumpHomePage(map.get("phone"), PlatformChannelConstants.PlatformChannelHWJ);
//                        }
//                        // 花无尽
//                        String hwjPlatformId = environment.getProperty("ddhua.promotion.hwj.platformId", String.class);
//                        if (hwjPlatformId.equals(platformId)) {
//                            userService.submitPhoneToHWJ(map.get("phone"), weixinAuth.getUserId(), Long.valueOf(hwjPlatformId));
//                            jumpHomeFlag = userService.judgeUserNeedJumpHomePage(map.get("phone"), PlatformChannelConstants.PlatformChannelHWJ);
//                        }
//                        // 大白钱包
//                        String dbqbPlatformId = environment.getProperty("ddhua.promotion.dbqb.platformId", String.class);
//                        if (dbqbPlatformId.equals(platformId)) {
//                            userService.submitPhoneToDBQB(map.get("phone"), weixinAuth.getUserId(), Long.valueOf(dbqbPlatformId));
//                            jumpHomeFlag = userService.judgeUserNeedJumpHomePage(map.get("phone"), PlatformChannelConstants.PlatformChannelDBQB);
//                        }
//                        // 有米管家
//                        String ymgjPlatformId = environment.getProperty("ddhua.promotion.ymgj.platformId", String.class);
//                        if (ymgjPlatformId.equals(platformId)) {
//                            userService.submitPhoneToYMGJ(map.get("phone"), weixinAuth.getUserId(), Long.valueOf(ymgjPlatformId));
//                            jumpHomeFlag = userService.judgeUserNeedJumpHomePage(map.get("phone"), PlatformChannelConstants.PlatformChannelYMGJ);
//                        }
//                        // 金小侠
//                        String jxxPlatformId = environment.getProperty("ddhua.promotion.jxx.platformId", String.class);
//                        if (jxxPlatformId.equals(platformId)) {
//                            userService.submitPhoneToJXX(map.get("phone"), weixinAuth.getUserId(), Long.valueOf(jxxPlatformId));
//                            jumpHomeFlag = userService.judgeUserNeedJumpHomePage(map.get("phone"), PlatformChannelConstants.PlatformChannelJXX);
//                        }
//                        // 用钱宝
//                        String yqbPlatformId = environment.getProperty("ddhua.promotion.yqb.platformId", String.class);
//                        if (yqbPlatformId.equals(platformId)) {
//                            User user = userMapper.getUserByUserId(weixinAuth.getUserId());
//                            userService.handlePhoneToYQB(map.get("phone"), user, Long.valueOf(yqbPlatformId));
//                            jumpHomeFlag = userService.judgeUserNeedJumpHomePage(user.getUsername(), PlatformChannelConstants.PlatformChannelYQB);
//                            if (!jumpHomeFlag) {
//                                String yqbh5Url = userService.getUserYQBH5Url(user.getUsername(), user, Long.valueOf(yqbPlatformId));
//                                return "redirect:" + yqbh5Url;
//                            }
//                        }
//
//                        // 小蓝卡
//                        String xlkPlatformId = environment.getProperty("ddhua.promotion.xlk.platformId", String.class);
//                        if (xlkPlatformId.equals(platformId)) {
//                            User user = userMapper.getUserByUserId(weixinAuth.getUserId());
//                            String userXLKH5Url = userService.getUserXLKH5Url(user.getUsername(), user, Long.valueOf(xlkPlatformId));
//                            if (StringUtils.isEmpty(userXLKH5Url)) {
//                                jumpHomeFlag = Boolean.TRUE;
//                            } else {
//                                return "redirect:" + userXLKH5Url;
//                            }
//                        }
//                    }
//                    if (jumpHomeFlag.booleanValue()) {
//                        return redirectUrl + "/#/?series" + series;
//                    }
//                } catch (Exception e) {
//                    log.warn("[推送第三方]Weixin 调用失败，phone={} track={}", map.get("phone"), track, e);
//                }
//            }
//
//            // 3、登录成功，则直接跳转第三方借款平台页面
//            if (SecurityUtils.getSubject().isAuthenticated() && StringUtils.isNotBlank(returnUrl)) {
//                String url = "redirect:" + URLDecoder.decode(returnUrl, "UTF-8");
//                if (url.contains("sinaweidai")) {
//                    url = url.replace("&amp;", "&");
//                }
//                log.info("[网页授权3]series={} url={}", series, url);
//                return url;
//            }
//
//            // 4.1、首次进来或openId与userId未绑定，则跳转flow登录页面
//            if (WxMenuKey.Login.equals(state) && (weixinAuth.getUserId() == null)) {
//                StringBuilder builder = new StringBuilder();
//                builder.append(redirectUrl).append("/#/flow?redirect=").append(URLEncoder.encode(returnUrl, "UTF-8"))
//                        .append("&openId=").append(openId).append("&track=").append(track)
//                        .append("&series=").append(series).append("&recommendType=").append(recommendType);
//                String url = builder.toString();
//                log.warn("[网页授权4]series={} the openId={} is unbind user, goto flow page url={}", series, openId, url);
//                return url;
//            }
//
//            // 4.2、首次进来或openId与userId未绑定，则跳转login登录页面
//            if (WxMenuKey.Login2.equals(state) && (weixinAuth.getUserId() == null)) {
//                StringBuilder builder = new StringBuilder();
//                builder.append(redirectUrl).append("/#/user/login?redirect=").append(URLEncoder.encode(returnUrl, "UTF-8"))
//                        .append("&openId=").append(openId).append("&series=").append(series);
//                String url = builder.toString();
//                log.warn("[网页授权4]series={} the openId={} is unbind user, goto #/user/login page url={}", series, openId, url);
//                return url;
//            }
//
//            // 5、其他情况，直接跳转贷超首页列表
//            String url = redirectUrl + "/#/?series" + series;
//            log.info("[网页授权5]series={} oauth2 url={} ", series, url);
//            return url;
//        } catch (Exception e) {
//            log.error("[网页授权9]oauth2 error {} {}", e.getMessage(), e);
//        }
//        return redirectUrl + "/#/?series" + series;
//    }
//
//    /**
//     * shiro登录，设置cookie
//     *
//     * @param userId
//     * @param response
//     */
//    private Map<String, String> handleLogin(Long userId, HttpServletResponse response) {
//        User user = userMapper.getUserByUserId(userId);
//        //生成token，并保存到数据库
//        String token = userTokenService.judgeToken(user.getRefId());
//        log.info("微信授权登陆token:{}", token);
//        //do login here
//        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().stop();
//        subject.getSession(true);
//        subject.login(new OAuth2Token(token));
//
//        Cookie cookie = new Cookie("ddhtk", token);
//        //表示5分钟有效
//        cookie.setMaxAge(1200);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        Map<String, String> map = new HashMap<>();
//        map.put("token", token);
//        map.put("phone", user.getUsername());
//
//        // check 是否为黑名单用户，check一次不再check
//        try {
//            Boolean button = environment.getProperty("ddhua.mars.risk.check.button", Boolean.class, true);
//            if (button) {
//                UserMarsRiskLog userMarsRiskLog = userMarsRiskLogMapper.getUserMarsRiskLog(user.getId());
//                if (null == userMarsRiskLog) {
//                    MarsRiskVo marsRiskResponse = marsRiskService.getMarsRiskResponse(user.getUsername());
//                    if (null != marsRiskResponse) {
//                        marsRiskService.recordMarsRiskRecord(user.getId(),marsRiskResponse);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            log.error("黑名单检测失败",e);
//        }
//
//        return map;
//    }
//
//    /**
//     * 插入用户登录日志
//     *
//     * @param userId
//     * @param openId
//     * @param series
//     * @param map
//     * @param track
//     * @param webId
//     * @param request
//     */
//    private void insertUserLoginLog(Long userId, String openId, String series, Map<String, String> map, String track, String webId, HttpServletRequest request) {
//        try {
//            UserLoginLog userLoginLog = new UserLoginLog();
//            userLoginLog.setCreated(new Date());
//            userLoginLog.setChannel(series);
//            userLoginLog.setOpenId(openId);
//            userLoginLog.setPhone(map.get("phone"));
//            userLoginLog.setUserId(userId);
//            userLoginLog.setToken(map.get("token"));
//            userLoginLog.setEUserId(webId);
//            userLoginLog.setTrack(track);
//            userLoginLog.setHeaders(JSONObject.toJSONString(getHeadersInfo(request)));
//
//            userLoginLogMapper.insert(userLoginLog);
//        } catch (Exception e) {
//            log.error("插入用户登录日志失败了", e);
//        }
//    }
//
//
//    /**
//     * 插入用户申请记录
//     *
//     * @param userId
//     * @param track
//     */
//    private void insertUserApplyLog(Long userId, String track, String series, String webId, Integer recommendType) {
//        try {
//            UserApplyLog userApplyLog = new UserApplyLog();
//            userApplyLog.setEUserId(webId);
//            userApplyLog.setSeries(series);
//            Integer isRecommend = recommendType;
//            if (isRecommend == null) {
//                if (StringUtils.isBlank(track)) {
//                    isRecommend = 0;
//                } else if (track.contains("first")) {
//                    isRecommend = 1;
//                }
//            }
//            userApplyLog.setIsRecommend(isRecommend);
//            Long platformId = StringUtils.isBlank(track) ? null : Long.valueOf(track.substring(track.lastIndexOf("-") + 1));
//            userApplyLog.setPlatformId(platformId);
//            if (platformId != null) {
//                LoanPlatformConfig loanPlatformConfig = loanPlatformConfigMapper.queryAllLoanPlatformConfigByIdV2(platformId);
//                userApplyLog.setPlatformName(loanPlatformConfig == null ? null : loanPlatformConfig.getPlatformName());
//            }
//            userApplyLog.setUserId(userId);
//            userApplyLogMapper.insert(userApplyLog);
//            log.info("插入用户申请记录成功了 userId={} eUserId={} platformId={}", userId, webId, platformId);
//        } catch (Exception e) {
//            log.error("插入用户申请记录出错了 userId={}", userId, e);
//        }
//    }
//
//    /**
//     * 获取请求头信息
//     *
//     * @param request
//     * @return
//     */
//    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
//        Map<String, String> map = new HashMap<String, String>();
//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            String value = request.getHeader(key);
//            map.put(key, value);
//        }
//        return map;
//    }

}

