package org.mybatis.result.resultTypeStrategy;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultListStrategy extends ResultDataStrategy {
    @Override
    public Object invoke(Method method, ResultSet resultSet) throws Exception {
        List<Object> objects = new ArrayList<>();
        ParameterizedType pt = (ParameterizedType) method.getGenericReturnType();
        //得到泛型里的class类型对象
        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];

        while (resultSet.next()) {
            Object instance = genericClazz.newInstance();
            super.generateResult(resultSet, instance);
            objects.add(instance);
        }
        return objects;
    }
}
