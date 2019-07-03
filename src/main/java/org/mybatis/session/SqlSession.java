package org.mybatis.session;

import org.mybatis.dataSources.ConnectionPoolHolder;
import org.mybatis.dataSources.DataSource;

public interface SqlSession {

    <T> T getMapper(Class<T> type);

    void setDataSource(DataSource dataSource);

    void putMapper(Class<?> clazz ,Object obj);

    void setConnectionPoolHolder(ConnectionPoolHolder holder);

    ConnectionPoolHolder getConnectionHolder();
}
