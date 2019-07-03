package org.mybatis.dataSources;

public class DataSource {
    private static DataSource dataSource = new DataSource();
    private DataSource(){
    }

    public String url;

    public String driverClass;

    public String userName;

    public String passWord;

    public static DataSource getInstall(){
        return dataSource;
    }

}
