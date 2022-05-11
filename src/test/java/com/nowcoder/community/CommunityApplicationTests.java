package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//指定CommunityApplication.class为配置类
class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;//ApplicationContext接口就是Spring容器

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test//测试Spring容器
    public void testApplicationContext() {
        System.out.println(applicationContext);
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//从容器中获取AlphaDao类型Bean，即一个java对象
        System.out.println(alphaDao.select());

        alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test//测试Bean的管理方式
    public void testBeanManagement(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);

        //被Spring容器管理的Bean默认是单例的，即只实例化一次，再次调用getBean获取的是同一个对象
        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test//测试第三方类的装配是否成功
    public void testBeanConfig() {
        SimpleDateFormat simpleDateFormat =
                applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Autowired//注解Spring容器自动注入AlphaDao到下面的属性alphaDao
    @Qualifier("alphaHibernate")//注入对应名字的Bean
    private AlphaDao alphaDao;

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Test//测试依赖注入
    public void testDI() {
        System.out.println(alphaDao);
        System.out.println(alphaService);
        System.out.println(simpleDateFormat);
    }
}
