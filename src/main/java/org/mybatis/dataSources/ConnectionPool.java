package org.mybatis.dataSources;

import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionPool {

    static ConnectionPoolHolder holder = new ConnectionPoolHolder();

    public static ConnectionPoolHolder createPool(DataSource dataSource) {
        try {
            Class.forName(dataSource.driverClass);
            for (int i = 0; i < 10; i++) {
                holder.threadPool.add(getConnection(dataSource));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holder;
    }

    public static Statement getConnection(DataSource dataSource) throws Exception {
        return DriverManager.getConnection(dataSource.url, dataSource.userName, dataSource.passWord).createStatement();
    }
}