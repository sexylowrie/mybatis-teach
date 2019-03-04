### SpringBoot集成MyBatis的使用以及源码浅析

演示环境

* mysql
* 构建工具maven

#### 项目演示

相对于spring集成mybatis，springboot集成mybatis在jar引用方面更加便捷，我们只用引入如下jar包即可：
```
<dependencies>
    <!-- springboot快速开始web服务 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.1.1.RELEASE</version>
    </dependency>
    <!-- 阿里数据源 -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.10</version>
    </dependency>
    <!-- mysql -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.15</version>
    </dependency>
    <!-- 快速开始mybatis -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.2</version>
    </dependency>
    <!-- 快速开始测试 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <version>2.1.1.RELEASE</version>
    </dependency>
</dependencies>
```

设置application.properties配置文件

```
# 设置数据源类型
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
# 用户名
spring.datasource.username=root
# 密码
spring.datasource.password=1qaz1qaz!QAZ
# 地址
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test
# 数据库驱动类型
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# 指定mybatis的mapper文件路径
mybatis.mapper-locations=classpath:/xin/sunce/mybatis/dao/*.xml
# 指定mybatis的实体路径
mybatis.type-aliases-package=xin.sunce.mybatis.entity
```

创建Application.java

```
package xin.sunce.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 设置dao层扫描注解
@MapperScan("xin.sunce.mybatis.dao")
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}

```

创建测试类

```
package xin.sunce.mybatis.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xin.sunce.mybatis.Application;
import xin.sunce.mybatis.entity.Customer;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CustomerDaoTest {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSelect() {
        Customer customer = customerDao.selectByPrimaryKey(1L);
        LOGGER.info(customer.toString());
    }


}
```
参考项目路径如下：

![springboot-mybatis.png](../images/springboot-mybatis.png)

#### 源码浅析

在mybatis-spring-boot-starter这个jar包帮我们做了什么呢？我们仔细研究一下：
```
<dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-autoconfigure</artifactId>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
    </dependency>
</dependencies>
```

我们发现mybatis-spring-boot-starter实质上就是一个pom文件，其中引入了spring-boot-starter，spring-boot-starter-jdbc，mybatis，mybatis-spring，mybatis-spring-boot-autoconfigure这些jar包，我们发现相较于spring整合mybatis多了mybatis-spring-boot-autoconfigure等jar包。我们稍后来看看autoconfigure到底做了什么？

我们来看mybatis-spring-boot-autoconfigure的项目结构

![mybatis-autoconfigure.png](../images/mybatis-autoconfigure.png)

核心类是MyBatisAutoConfiguration.java

```
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnBean({DataSource.class})
//读入配置文件
@EnableConfigurationProperties({MybatisProperties.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class MybatisAutoConfiguration {

    //BeanFactory中无SqlSessionFactory实例时匹配，创建sqlSessionFactory实例
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){...}

    //BeanFactory中无SqlSession实例时匹配，创建sqlSessionTemplate实例
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {...}
    
    //BeanFactory中无MapperFactoryBean实例时匹配，实现mapper文件的注册与配置
    @Configuration       @Import({MybatisAutoConfiguration.AutoConfiguredMapperScannerRegistrar.class})
    @ConditionalOnMissingBean({MapperFactoryBean.class})
    public static class MapperScannerRegistrarNotFoundConfiguration {...}

}
```

以上便是MyBatis在springboot中自动配置的核心，而springboot是如何实现自动配置的，我们会在后面的文章中仔细的挖掘。




