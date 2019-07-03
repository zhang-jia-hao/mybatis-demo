package org.mybatis.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {


    public static String paramsJoin(String sql,Object[] args){
        Pattern p = Pattern.compile("\\$|\\#\\{.*\\}");
        Matcher m= p.matcher(sql);

        while(m.find()){
            sql = sql.replace(m.group(0),"'"+args[0]+"'");
        }
        return sql;
    }

    public static void main(String[] args) {
        System.out.println(paramsJoin("select * from test where id = ${id}", new Object[]{1}));
    }


}
