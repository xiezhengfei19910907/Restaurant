/**
 * 这是对菜单的操作和管理
 */
package com.res.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import com.res.db.SqlHelper;

public class MenuModel extends AbstractTableModel {

    SqlHelper sqlHelper;
    Vector colums;
    Vector rows;
    ResultSetMetaData rsmt = null;
    //写一个方法，用于查询需要显示的人事信息
    //对query修改，让其有更好的通用性
    String Str2 = null;

    //增 删 改
    public boolean upDate(String sql, String paras[]) {
        //创建一个SqlHelper
        sqlHelper = new SqlHelper();
        return sqlHelper.exeUpdate(sql, paras);
    }

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

    public void queryStr(String sql, String Str2) {//Str2的大小写不能错，又花费很长时间来找错！！！！
        String sql2 = "select *  from menu where abbreviation=? or name=? or category=?";
        String[] paras = {Str2, Str2, Str2};
        this.query(sql2, paras);
    }
}