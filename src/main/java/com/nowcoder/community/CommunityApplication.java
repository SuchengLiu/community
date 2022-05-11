package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//配置类
@SpringBootApplication
public class CommunityApplication {

    public static void main(String[] args) {
        //启动Tomcat，创建IoC容器
        SpringApplication.run(CommunityApplication.class, args);
    }

}
