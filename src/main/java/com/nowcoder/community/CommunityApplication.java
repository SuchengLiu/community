package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

//配置类
@SpringBootApplication
public class CommunityApplication {

    @PostConstruct
    public void init() {
        // 解决redis与elasticsearch使用netty冲突的问题
        // see Netty4Utils.setAvailableProcessors()
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {
        //启动Tomcat，创建IoC容器
        SpringApplication.run(CommunityApplication.class, args);
    }

}
