package org.mybatis.result.resultTypeStrategy;

import java.lang.reflect.Method;
import java.sql.ResultSet;

public class ResultIntStrategy extends ResultDataStrategy {
    @Override
    public Object invoke(Method method, ResultSet resultSet) throws Exception {
        return resultSet.getInt(1);
    }
}
