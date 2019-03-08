package com.boot.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Slf4j
public class CommonUtil {
    private static Properties props;

    static {
        String fileName = "common.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(CommonUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            log.error("read common.propertites异常", e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
