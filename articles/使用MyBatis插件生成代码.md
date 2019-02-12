### 使用MyBatis插件生成代码

#### 简介
MyBatis Generator（MBG） 是一个代码生成工具，它支持所有版本的MyBatis和2.2.0以上版本的iBatis；MBG旨在构造生成大多数常用的数据库表操作，例如简单的CURD（增删改查），至于复杂的关联查询以及存储过程还是需要手动实现的。

MyBatis Generator 会生成如下代码：

* POJOS（plain old java objects）
  * Entity    
  * BLOB  包含BLOB的字段
  * Example 动态操作select，insert，update，delete

* XML Mapper 文件
  * insert
  * select by primary key
  * select
  * update by primary key
  * update
  * delete by primary key
  * delete

* Dao 层 文件
  * 对应Mapper文件的dao 文件

#### 依赖条件

 MyBatis Generator 的运行依赖一下条件
 
 * jdk1.6+

 * JDBC Driver

#### 快速入门

想要快速的运行 MyBatis Generator ，你需要遵从一下步骤：

* 创创建一个配置文件，最少必须包含以下属性
  * <jdbcConnection> 此元素用于指定数据库连接信息
  * <javaModelGenerator> 此元素用于指定生成的模型信息Entity，Example
  * <sqlMapGenerator> 此元素用于指定Mapper XMl 文件信息
  * <javaClientGenerator> 此元素用于指定dao 层的信息
  * <table> 用于指定想要生成表信息


* 将以上配置文件保存在合适的地方 (例如 \temp\generatorConfig.xml)

* 运行MyBatis Generator
  
```
 java -jar mybatis-generator-core-x.x.x.jar -configfile \temp\generatorConfig.xml -overwrite
```

* 执行完MBG命令，你在创建一个MyBatis/iBatis配置文件就可以集成使用MyBatis/iBatis了;[MyBatis/iBatis配置文件参考](http://www.mybatis.org/generator/afterRunning.html)
  
#### 运行MyBatis Generator

运行MBG的方法有很多种，例如：通过Maven plugin，通过ant，通过命令行，通过java代码；下文将介绍通过java代码的方式运行，其他运行方式可以[点这里](http://www.mybatis.org/generator/running/running.html)

```
/**
* 方式一：java 使用配置文件方式
*/
List<String> warnings = new ArrayList<String>();
boolean overwrite = true;
//注意配置文件路径，一般置于src/main/resources/generatorConfig.xml
File configFile = new File("generatorConfig.xml");
ConfigurationParser cp = new ConfigurationParser(warnings);
Configuration config = cp.parseConfiguration(configFile);
DefaultShellCallback callback = new DefaultShellCallback(overwrite);
MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
generator.generate(null);

/**
* 方式二：java 不使用配置文件方式
*/
List<String> warnings = new ArrayList<String>();
boolean overwrite = true;
Configuration config = new Configuration();
//用属性填充config object 
DefaultShellCallback callback = new DefaultShellCallback(overwrite);
MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
generator.generate(null);
```

#### XML 配置文件指引

此处介绍常用的jdbcConnection，javaModelGenerator，sqlMapGenerator，javaClientGenerator，table；其余元素设置[参考](http://www.mybatis.org/generator/configreference/xmlconfig.html)
* <jdbcConnection>
   * 必填参数：driverClass，connectionURL
   * 选填参数：userId，password
    
示例如下：
```
<jdbcConnection driverClass="com.mysql.jdbc.Driver"
    connectionURL="jdbc:mysql://127.0.0.1:3306/test"
    userId="root"
    password="1qaz1qaz!QAZ">
</jdbcConnection>
```
    
* <javaModelGenerator>
   * 必填参数：targetPackage，targetProject

示例如下：
```
<!-- entity保存地址 targetPackage：目标包结构，targetProject：目标路径-->
<javaModelGenerator targetPackage="xin.sunce.mybatis.entity" targetProject="src/main/java">
    <property name="enableSubPackages" value="true" />
    <property name="trimStrings" value="true" />
</javaModelGenerator>
```

* <sqlMapGenerator>
   * 必填参数：targetPackage，targetProject

示例如下：
```
<!-- xml保存地址 targetPackage：目标包结构，targetProject：目标路径-->
<sqlMapGenerator targetPackage="xin.sunce.mybatis.dao"          
    targetProject="src/main/resources">
            <property name="enableSubPackages" value="true" />
</sqlMapGenerator>
```

* <javaClientGenerator>
    * 必填参数：type，targetPackage，targetProject
```
<!-- dao保存地址 -->
<javaClientGenerator type="XMLMAPPER" targetPackage="xin.sunce.mybatis.dao"  
    targetProject="src/main/java">
    <property name="enableSubPackages" value="true" />
</javaClientGenerator>
```

[本文章参考MyBatis-Generator官网](http://www.mybatis.org/generator/index.html)