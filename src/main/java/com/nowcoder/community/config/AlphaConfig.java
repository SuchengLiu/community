package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration//配置类，用于装配第三方的Bean
public class AlphaConfig {

    @Bean//将java自带的SimpleDateFormat类装配到项目中，其中方法名simpleDateFormat就是Bean的名字
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
