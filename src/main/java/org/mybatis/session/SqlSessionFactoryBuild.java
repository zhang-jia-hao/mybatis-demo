package org.mybatis.session;

import org.mybatis.annotation.Configuration;
import org.mybatis.annotation.DataSourcesConfig;
import org.mybatis.annotation.Mapper;
import org.mybatis.dataSources.ConnectionPool;
import org.mybatis.dataSources.DataSource;
import org.mybatis.proxy.MapperProxy;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import java.lang.reflect.Method;
import java.util.Set;

public class SqlSessionFactoryBuild {

    static SqlSession sqlSession = new DefaultSqlSession();

    static String mapperScannerPath = "";

    static Class<?> configuration;

    public static <T> T build(Class<?> configClazz, Class<?> mapperClazz) {
        SqlSession build = build(configClazz);
        return (T) build.getMapper(mapperClazz);
    }

    public static SqlSession build(Class<?> configClazz) {
        //加载配置项
        doLoadConfig(configClazz);
        //加载数据源
        doLoadDataSources();
        //加载mapper映射
        doLoadMapper();

        return sqlSession;
    }

    private static void doLoadConfig(Class<?> configClazz) {
        mapperScannerPath = configClazz.getAnnotation(Configuration.class).scannerMapperPackages();
        configuration = configClazz;
    }

    /**
     * 创建数据源
     * 默认在主配置下查找注解（Configuration注解下查找）
     */
    private static void doLoadDataSources() {
        Method[] methods = configuration.getMethods();

        for (Method method : methods) {
            if (!method.isAnnotationPresent(DataSourcesConfig.class)) {
                continue;
            }
            DataSourcesConfig annotation = method.getAnnotation(DataSourcesConfig.class);
            DataSource dataSource = assemblyDataSource(annotation);
            sqlSession.setDataSource(dataSource);
            sqlSession.setConnectionPoolHolder(ConnectionPool.createPool(dataSource));
        }
    }

    /**
     * 根据注解，生成对应mapper代理
     */
    private static void doLoadMapper() {
        Reflections reflection = new Reflections(mapperScannerPath, new SubTypesScanner(), new TypeAnnotationsScanner());
        Set<Class<?>> typesAnnotatedWith = reflection.getTypesAnnotatedWith(Mapper.class);

        for (Class clazz : typesAnnotatedWith) {
            sqlSession.putMapper(clazz, MapperProxy.getProxyObj(clazz, sqlSession));
        }
    }

    /**
     * 组装dataSource对象
     * @param annotation
     * @return
     */
    private static DataSource assemblyDataSource(DataSourcesConfig annotation) {
        DataSource sources = DataSource.getInstall();
        sources.url = annotation.url();
        sources.driverClass = annotation.dirver();
        sources.userName = annotation.username();
        sources.passWord = annotation.password();
        return sources;
    }
}
