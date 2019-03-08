package com.boot.demo.weixin.utils;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author liangfeihu
 */
public class JsonUtils {

    /**
     * 美化数据输出
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return WxMpGsonBuilder.create().toJson(obj);
    }
}
