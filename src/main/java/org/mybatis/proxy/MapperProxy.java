package org.mybatis.proxy;

import org.mybatis.annotation.Select;
import org.mybatis.dataSources.ConnectionPoolHolder;
import org.mybatis.result.ResultSetHandler;
import org.mybatis.session.SqlSession;
import org.mybatis.util.StringUtils;
import java.lang.reflect.Proxy;
import java.sql.ResultSet;
import java.sql.Statement;

public class MapperProxy {

    public static <T> T getProxyObj(Class<?> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {
            ConnectionPoolHolder holder = sqlSession.getConnectionHolder();
            ResultSet resultSet = null;
            if (method.isAnnotationPresent(Select.class)) {
                String sql = method.getAnnotation(Select.class).value();
                Statement statement = getStatement(holder);
                resultSet = statement.executeQuery(StringUtils.paramsJoin(sql,args));
                holder.close(statement);
            }
            return ResultSetHandler.parseResultMap(method, resultSet);
        });
    }

    private static Statement getStatement(ConnectionPoolHolder holder) {
        Statement statement = null;
        while (statement == null) {
            if (holder.next()) {
                statement = holder.getStatement();
            }
        }
        return statement;
    }
}
