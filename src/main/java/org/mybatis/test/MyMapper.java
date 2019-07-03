package org.mybatis.test;

import org.mybatis.annotation.Mapper;
import org.mybatis.annotation.Select;

import java.util.List;

@Mapper
public interface MyMapper {
    @Select("select * from test where id = #{id}")
    Testbean select(int id);

    @Select("select name from test where id = 2")
    String selectName();

    @Select("select * from test where id = 2")
    Testbean select();

    @Select("select * from test")
    List<Testbean> selectList();

    @Select("select * from test where name = #{name}")
    List<Testbean> selectByName(String name);
}
