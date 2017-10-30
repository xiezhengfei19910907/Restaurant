/**
 * 这是用户表数据模型，用它以完成对用户的各种操作，这是主要编写项目需要的业务操作
 */
package com.res.model;

import com.res.db.*;

import java.sql.*;

public class UserModel {
    public String checkUser(String clerkid, String password) {
        String zhiwei = "";
        SqlHelper sp = null;
        try {
            String sql = "select zhiwei from UserLogin where clerkid=? and password=?";
            String paras[] = {clerkid, password};
            sp = new SqlHelper();
            //从SqlHelper类中得到数据库的结果集
            ResultSet rs = sp.query(sql, paras);

            if (rs.next()) {
                //如果进去，则取出职位
                zhiwei = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sp.close();
        }
        return zhiwei;
    }
}