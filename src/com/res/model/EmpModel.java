/*
 * 这是人事数据模型，完成对人事的各种操作
 */
package com.res.model;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;

import com.res.db.SqlHelper;

public class EmpModel extends AbstractTableModel {
    SqlHelper sqlHelper;
    Vector<String> colums;
    Vector<Vector> rows;
    ResultSet rs = null;

    //存放查询文本框的文本
    String Str = null;

    //增 删 改
    public boolean upDate(String sql, String paras[]) {
        //创建一个SqlHelper
        sqlHelper = new SqlHelper();
        return sqlHelper.exeUpdate(sql, paras);
    }

    /**
     * 查询需要显示的人事信息
     * @param sql
     * @param paras
     */
    public void query(String sql, String paras[]) {
        //初始化列
        this.colums = new Vector<String>();
        this.rows = new Vector<Vector>();

        //创建sqlHelper对象
        sqlHelper = new SqlHelper();
        ResultSet rs = sqlHelper.query(sql, paras);
        try {
            ////从rs对象中可以得到一个ResultSetMetaData
            //rsmt可以得到结果有多少列
            ResultSetMetaData rsmt = rs.getMetaData();
            for (int i = 0; i < rsmt.getColumnCount(); i++) {
                this.colums.add(rsmt.getColumnName(i + 1));
            }

            //把rs结果放入到rows
            while (rs.next()) {
                Vector<String> temp = new Vector<String>();

                for (int i = 0; i < rsmt.getColumnCount(); i++) {
                    temp.add(rs.getString(i + 1));
                }
                rows.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.close();
        }
    }

    public int getRowCount() {
        return this.rows.size();
    }

    public int getColumnCount() {
        return this.colums.size();
    }

    public Object getValueAt(int arg0, int arg1) {
        return ((Vector) rows.get(arg0)).get(arg1);
    }

    public String getColumnName(int arg0) {
        return this.colums.get(arg0).toString();
    }

    public void queryStr(String sql, String Str) {
        String sql1 = "select *  from renshiziliao  where clerkid=? or name=? or zhiwei=?";
        String[] paras = {Str, Str, Str};
        this.query(sql1, paras);
    }
}