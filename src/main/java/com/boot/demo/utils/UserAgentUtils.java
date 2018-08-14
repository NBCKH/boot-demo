package com.boot.demo.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取User-Agent
 *
 */
public class UserAgentUtils {

    static Pattern pattern = Pattern.compile("[0-9]\\.[0-9]\\.[0-9]");

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static boolean isApp(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        if (StringUtils.isNotEmpty(userAgent)) {
            String agent = userAgent.toLowerCase();
            if (agent.contains("com.diandianhua.ios.v") || agent.contains("com.diandianhua.android.v")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCashbusApp(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        if (StringUtils.isNotEmpty(userAgent)) {
            String agent = userAgent.toLowerCase();
            if (agent.contains("com.cashbus.ios.swhj.v") || agent.contains("com.cashbus.android.swhj.v")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFromWeixin(String userAgent) {
        if (StringUtils.isNotEmpty(userAgent)) {
            String agent = userAgent.toLowerCase();
            if ( !agent.contains("com.diandianhua.ios.v") && !agent.contains("com.diandianhua.android.v")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIOS (HttpServletRequest request) {
        String ua = UserAgentUtils.getUserAgent(request);
        if (ua.contains("ios")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAndroid (HttpServletRequest request) {
        String ua = UserAgentUtils.getUserAgent(request);
        if (ua.contains("android")) {
            return true;
        } else {
            return false;
        }
    }

    public static Integer getVersionCode (HttpServletRequest request) {
        String ua = getUserAgent(request);
        Matcher matcher = pattern.matcher(ua);
        if (matcher.find()) {
            String version = matcher.group();
            version = version.replaceAll("\\.", "0");
            return Integer.valueOf(version);
        } else {
            return null;
        }
    }
}
