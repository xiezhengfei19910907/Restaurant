/**
 * 功能: 数据库操作
 * 作者: 谢正飞
 * 时间: 2013.04.30
 * 
 * 注意：如果连接数据库时出现 java.lang.ClassNotFoundException: com.mysql.jdbc.Driver 则表示未引入JAR驱动包
 */
package com.res.db;

import java.sql.*;

public class SqlHelper {
	
    /**
     * 定义需要的对象
     */
    Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * mysql database
     */
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/Restaurants?&useSSL=false";
    String user = "root";
    String password = "root";

    /**
     * 构造函数，初始化ct
     */
    public SqlHelper() {
        try {
            //加载驱动
            Class.forName(driver);
            //得到连接
            ct = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增删改
     * @param sql
     * @param paras
     * @return
     */
    public boolean exeUpdate(String sql, String[] paras) {
        boolean b = true;
        try {
            ps = ct.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                ps.setString(i + 1, paras[i]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 通过赋值方式可以防止漏洞注入方式，保证安全性
     * @param sql
     * @param params
     * @return
     */
    public ResultSet query(String sql, String[] params) {
        try {
            ps = ct.prepareStatement(sql);
            
            //对sql的参数赋值
            if (params.length > 0) {
	            for (int i = 0; i < params.length; i++) {
	                ps.setString(i + 1, params[i]);
	            }
            }
            //执行查询
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 关闭资源的方法
     */
    public void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (ct != null) ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
