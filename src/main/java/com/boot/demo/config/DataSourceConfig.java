package com.boot.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author chenkaihua
 * @date 2018/8/1 10:32
 * 阿里数据库链接池的启动bean配置
 */
@Configuration
public class DataSourceConfig {

    @Bean("rootDataSource")
    @ConfigurationProperties("spring.datasource.druid.bootdemo")
    public DataSource rootDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
