package org.mybatis.dataSources;

import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPoolHolder {

    Queue<Statement> threadPool = new LinkedList<>();
    public synchronized boolean next() {
        return threadPool.size() > 0;
    }

    public synchronized Statement getStatement() {
       // System.out.println("当前剩余连接数："+ threadPool.size());
        return threadPool.poll();
    }

    public void close(Statement statement) {
        threadPool.add(statement);
    }

}
