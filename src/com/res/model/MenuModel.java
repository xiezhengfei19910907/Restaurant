package com.res.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import com.res.db.SqlHelper;

/**
 * 这是对菜单的操作和管理
 * @author allen
 *
 */
public class MenuModel extends AbstractTableModel {

    SqlHelper sqlHelper;
    Vector colums;
    Vector rows;
    ResultSetMetaData rsmt = null;
    String Str2 = null;

    /**
     * 公共更新方法
     * @param sql
     * @param params
     * @return
     */
    public boolean upDate(String sql, String params[]) {
        sqlHelper = new SqlHelper();
        return sqlHelper.exeUpdate(sql, params);
    }

    public void query(String sql, String params[]) {
        //初始化列
        this.colums = new Vector();
        this.rows = new Vector();
        SqlHelper sh = new SqlHelper();
        ResultSet rs = sh.query(sql, params);
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

    /**
     * 根据指定条件查询菜谱信息
     * @param Str
     */
    public void getMenuInfoByCondition(String Str) {
        String sql = "select abbreviation,name,category,price,material,remark,sale from menu where abbreviation=? or name=? or category=?";
        String[] params = {Str, Str, Str};
        
        this.query(sql, params);
    }
    
    /**
     * 获取所有的菜谱信息
     */
    public void getMenuInfo() {
    		String sql = "select abbreviation,name,category,price,material,remark,sale from menu";
    		String[] params = {};
    		
    		this.query(sql, params);
    }
    
    /**
     * 新增菜谱信息
     * @param params
     * @return
     */
    public boolean insertMenuInfo(String[] params) {
    		String sql = "insert into menu values(?,?,?,?,?,?,?)";
    		
    		return this.upDate(sql, params);
    }
    
    /**
     * 修改指定的菜谱信息
     * @param params
     * @return
     */
    public boolean updateMenuInfo(String[] params) {
    		String sql = "update menu set name=?,category=?,price=?,material=?,remark=?,sale=? where abbreviation=?";
    		
    		return this.upDate(sql, params);
    }
    
    /**
     * 删除指定的菜谱信息
     * @param params
     * @return
     */
    public boolean deleteMenuInfo(String[] params) {
    		String sql = "delete from menu where abbreviation = ?";
    		
    		return this.upDate(sql, params);
    }
    
    
}