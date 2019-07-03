package org.mybatis.result.resultTypeStrategy;

import java.lang.reflect.Method;
import java.sql.ResultSet;

public class ResultObjectStrategy extends ResultDataStrategy {
    @Override
    public Object invoke(Method method, ResultSet resultSet) throws Exception {
        Object instance = method.getReturnType().newInstance();
        super.generateResult(resultSet, instance);
        return instance;
    }
}
