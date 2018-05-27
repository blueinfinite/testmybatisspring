package com.blueinfinite.config;

import com.blueinfinite.Service.MessageService;
import com.blueinfinite.utils.ConfigDBUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * 初始配置
 */
@Configuration
public class Config {
    /**
     * 配置yal支持
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        System.out.println("PropertySourcesPlaceholderConfigurer");

        //获取yml文件
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));

        //使用yml生效
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        p.setProperties(yaml.getObject());

        return p;
    }

    /**
     * spring 注入测试
     *
     * @return
     */
    @Bean
    MessageService mockMessageService() {
        return () -> "hello";
    }

    /**
     * 配置数据源
     *
     * @param info 数据库配置信息
     * @return
     */
    @Bean
    public DataSource dataSource(ConfigDBInfo info) {
        System.out.println(info.toString());
        return ConfigDBUtils.getDruid(info);
    }

//    @Bean
//    JdbcTransactionFactory jdbcTransactionFactory() {
//        return new JdbcTransactionFactory();
//    }

    /**
     * 利用SqlSessionFactoryBean，绑定mapper(xml)位置
     * @param dataSource 数据源
     * @return
     */
    @Bean("SqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        return ConfigDBUtils.getSqlSessionFactoryBean(dataSource, "classpath*:mapper/*.xml");
    }

//    /**
//     * mybatis会话工厂
//     */
//    @Bean
//    SqlSessionFactory getSqlSessionFactory(JdbcTransactionFactory jdbcTrans, DataSource dataSource) {
//        System.out.println("getSqlSessionFactory");
//        return ConfigDBUtils.getSqlSessionFactory("test_mybatis", "classpath*:mapper/*.xml", jdbcTrans, dataSource);
//    }

    /**
     * mapper配置扫描并绑定
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //SqlSessionFactory名称即是SqlSessionFactoryBean的指定bean名
        return ConfigDBUtils.getMapperScannerConfigurer("SqlSessionFactory", "com.blueinfinite.mapper");
    }
}

