package org.mybatis.session;

import org.mybatis.dataSources.ConnectionPoolHolder;
import org.mybatis.dataSources.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSqlSession implements SqlSession {
    public DataSource dataSource;
    public ConnectionPoolHolder holder;

    public Map<Class, Object> mapperFactory = new ConcurrentHashMap<>();

    public <T> T getMapper(Class<T> type) {
        return (T) mapperFactory.get(type);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void putMapper(Class<?> clazz, Object obj) {
        mapperFactory.put(clazz, obj);
    }

    @Override
    public void setConnectionPoolHolder(ConnectionPoolHolder holder) {
        this.holder = holder;
    }

    @Override
    public ConnectionPoolHolder getConnectionHolder() {
        return this.holder;
    }
}
