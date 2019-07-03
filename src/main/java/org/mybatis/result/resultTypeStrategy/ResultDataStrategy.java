package org.mybatis.result.resultTypeStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResultDataStrategy{
    public abstract Object invoke(Method method,ResultSet resultSet)throws Exception;

    public static void generateResult(ResultSet resultSet, Object instance) throws IllegalAccessException, SQLException {
        for (Field field : instance.getClass().getFields()) {
            field.setAccessible(true);
            String fieldType = field.getGenericType().toString();
            if (fieldType.endsWith(".String")) {
                field.set(instance, resultSet.getString(field.getName()));
            } else {
                field.set(instance, resultSet.getInt(field.getName()));
            }
        }
    }

    public Object getResultData(Method method,ResultSet resultSet)throws Exception{
        if(!method.getReturnType().getName().endsWith(".List")){
            resultSet.next();
        }
        return invoke(method,resultSet);
    }
}