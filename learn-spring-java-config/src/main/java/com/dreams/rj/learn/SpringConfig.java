package com.dreams.rj.learn;

import com.dreams.rj.learn.dao.UserInfoDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//@Configuration
@ComponentScan(
        basePackages = "com.dreams.rj.learn",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserInfoDao.class),
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*stub.*Repository")
) //配置扫描的包
public class SpringConfig {

    @Bean
    public UserInfoDao configUserInfo() {
        return new UserInfoDao();
    }
}
