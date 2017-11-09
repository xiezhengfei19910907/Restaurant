package com.res.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 数据库操作
 * @author allen
 * 
 */
public class SqlHelper {
	
    /**
     * 定义需要的对象
     */
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    /**
     * mysql config
     */
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/Restaurants?useSSL=false";
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
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增删改
     * @param sql
     * @param params
     * @return
     */
    public boolean exeUpdate(String sql, String[] params) {
        boolean b = true;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
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
            preparedStatement = connection.prepareStatement(sql);
            
            //对sql的参数赋值
            if (params.length > 0) {
	            for (int i = 0; i < params.length; i++) {
	                preparedStatement.setString(i + 1, params[i]);
	            }
            }
            //执行查询
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 关闭资源的方法
     */
    public void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
