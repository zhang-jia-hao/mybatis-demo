package org.mybatis.test;

import org.mybatis.annotation.Configuration;
import org.mybatis.annotation.DataSourcesConfig;

@Configuration(scannerMapperPackages = "org.mybatis.test")
public class MybatisConfig {

    @DataSourcesConfig(dirver = "com.mysql.jdbc.Driver",url="jdbc:mysql://localhost:3306/test",username = "root",password = "123456")
    public void dataSource(){}

}
