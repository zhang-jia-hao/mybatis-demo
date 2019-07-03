package org.mybatis.test;

public class Testbean {
    public int id;
    public String name;

    @Override
    public String toString() {
        return "Testbean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}