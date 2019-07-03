package org.mybatis.test;

import org.mybatis.session.SqlSessionFactoryBuild;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws Exception {
        //Class.forName("com.mysql.jdbc.Driver");
        /*Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from test where id = 1");

        while(resultSet.next()){
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
        }
*/
        MyMapper myMapper = SqlSessionFactoryBuild.build(MybatisConfig.class, MyMapper.class);
        System.out.println(Arrays.toString(myMapper.selectList().toArray()));
        System.out.println(myMapper.select());
        System.out.println(myMapper.selectName());
        System.out.println(myMapper.select(1));
        System.out.println(myMapper.selectByName("c"));
        System.out.println("Thread start ...........");
        for (int i = 0; i < 22; i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + myMapper.select(1).toString())).start();
        }

    }
}
