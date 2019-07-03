package org.mybatis.result.resultTypeStrategy;

import java.lang.reflect.Method;
import java.sql.ResultSet;

public class ResultStringStrategy extends ResultDataStrategy {
    @Override
    public Object invoke(Method method, ResultSet resultSet) throws Exception {
        return resultSet.getString(1);
    }
}
