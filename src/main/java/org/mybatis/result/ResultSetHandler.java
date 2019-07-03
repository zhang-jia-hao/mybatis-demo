package org.mybatis.result;

import org.mybatis.result.resultTypeStrategy.ResultDataStrategy;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHandler {

    public static Object parseResultMap(Method method, ResultSet resultSet) {
        try {
            Class<?> returnType = method.getReturnType();
            ResultDataStrategy resultDataStrategy = ResultStrategyFactory.getResultType(returnType);
            return resultDataStrategy.getResultData(method,resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
