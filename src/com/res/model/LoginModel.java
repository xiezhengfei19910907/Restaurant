/*
 * 登录管理模型，完成对登录的管理
 */
package com.res.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import com.res.db.SqlHelper;

public class LoginModel extends AbstractTableModel {
    SqlHelper sqlHelper;
    Vector colums;
    Vector rows;
    ResultSetMetaData rsmt = null;
    //写一个方法，用于查询需要显示的人事信息
    //对query修改，让其有更好的通用性
    //存放查询文本框的文本
    String St = null;

    public void query(String sql, String paras[]) {
        //初始化列
        this.colums = new Vector();
        this.rows = new Vector();
        SqlHelper sh = new SqlHelper();
        ResultSet rs = sh.query(sql, paras);
        //从rs对象中可以得到一个
        try {
            rsmt = rs.getMetaData();
            for (int i = 0; i < rsmt.getColumnCount(); i++) {
                this.colums.add(rsmt.getColumnName(i + 1));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        try {
            while (rs.next()) {
                Vector temp = new Vector();
                for (int i = 0; i < rsmt.getColumnCount(); i++) {
                    temp.add(rs.getString(i + 1));
                }
                if ((temp.get(rsmt.getColumnCount() - 1)) != null) {
                    temp.set(rsmt.getColumnCount() - 1, "密码");
                }
                rows.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sh.close();
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.colums.get(column).toString();
    }

    public int getColumnCount() {
        return this.colums.size();
    }

    public int getRowCount() {
        return this.rows.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Vector) rows.get(rowIndex)).get(columnIndex);
    }

    public boolean upDate(String sql, String paras[]) {
        //创建一个SqlHelper（如果不考虑程序的并发性，可以把sqlhelper做成static）
        sqlHelper = new SqlHelper();
        return sqlHelper.exeUpdate(sql, paras);
    }

    public void queryStr(String sql, String St) {
        String sql0 = "select clerkid,name,zhiwei,password  from UserLogin  where clerkid=? or zhiwei=?";
        String[] paras = {St, St};//此处两个变量，调了好大会才发现错误
        this.query(sql0, paras);
    }
    
    /**
     * 检查用户是否有登录的权限
     * @param clerkid
     * @param password
     * @return
     */
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