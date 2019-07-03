package org.mybatis.result;

import org.mybatis.result.resultTypeStrategy.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultStrategyFactory {

    private static Map<Class,ResultDataStrategy> factory = new ConcurrentHashMap();
    static{
        factory.put(java.lang.String.class,new ResultStringStrategy());
        factory.put(int.class,new ResultIntStrategy());
        factory.put(java.util.List.class,new ResultListStrategy());
        factory.put(Object.class,new ResultObjectStrategy());
    }

    public static ResultDataStrategy getResultType(Class clazz){
        return factory.get(clazz) == null ? factory.get(Object.class) : factory.get(clazz);
    }

}
