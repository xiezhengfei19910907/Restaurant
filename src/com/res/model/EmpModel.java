/**
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

    /**
     * 公共更新数据方法
     * @param sql
     * @param params
     * @return
     */
    public boolean upDate(String sql, String params[]) {
        sqlHelper = new SqlHelper();
        return sqlHelper.exeUpdate(sql, params);
    }

    /**
     * 查询需要显示的人事信息
     * @param sql
     * @param params
     */
    public void query(String sql, String params[]) {
        //初始化列
        this.colums = new Vector<String>();
        this.rows = new Vector<Vector>();

        //创建sqlHelper对象
        sqlHelper = new SqlHelper();
        ResultSet rs = sqlHelper.query(sql, params);
        try {
            //从rs对象中可以得到一个ResultSetMetaData
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

    /**
     * 根据条件查询员工信息
     * @param Str
     */
    public void getEmpInfoByCondition(String Str) {
        String sql = "select clerkid,name,sex,address,birth,id,xueli,zhiwei,hunfou from renshiziliao where clerkid=? or name=? or zhiwei=?";
        String[] params = {Str, Str, Str};
        
        this.query(sql, params);
    }
    /**
     * 获取所有的员工
     */
    public void getEmpInfo() {
    		String sql = "select clerkid,name,sex,address,birth,id,xueli,zhiwei,hunfou from renshiziliao";
    		String[] params = {};
    		
    		this.query(sql, params);
    }
    
    /**
     * 新增人事资料
     * @param params
     * @return
     */
    public boolean insertEmpInfo(String[] params) {
    		String sql = "insert into renshiziliao values(?,?,?,?,?,?,?,?,?)";
    		
    		return this.upDate(sql, params);
    }
    
    /**
     * 删除指定的员工
     * @param params
     * @return
     */
    public boolean deleteEmpInfo(String[] params) {
    		String sql = "delete from renshiziliao where clerkid=?";
    		
    		return this.upDate(sql, params);
    }
    
    /**
     * 更新指定的员工
     * @param params
     * @return
     */
    public boolean updateEmpInfo(String[] params) {
    		String sql = "update renshiziliao set name=?,sex=?,address=?,birth=?,id=?,xueli=?,zhiwei=?,hunfou=? where clerkid=?";
    		
    		return this.upDate(sql, params);
    }
}