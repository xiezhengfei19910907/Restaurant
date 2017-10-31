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

    /**
     * 公共查询方法
     * @param sql
     * @param params
     */
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

    /**
     * 根据姓名或员工号或职位查询登录信息
     * @param St
     */
    public void getLoginInfoByCondition(String St) {
        String sql = "select clerkid,name,zhiwei,password from UserLogin where name=? or clerkid=? or zhiwei=?";
        String[] params = {St, St, St};
        this.query(sql, params);
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
            String params[] = {clerkid, password};
            sp = new SqlHelper();
            //从SqlHelper类中得到数据库的结果集
            ResultSet rs = sp.query(sql, params);

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
    
    /**
     * 新增登录用户信息
     * @param params
     * @return
     */
    public boolean insertLoginInfo(String[] params) {
    		String sql = "insert into UserLogin values(?,?,?,?)";
    		
    		return this.upDate(sql, params);
    }
    
    /**
     * 更新指定的登录用户信息
     * @param params
     * @return
     */
    public boolean updateLoginInfo(String[] params) {
    		String sql = "update UserLogin set name=?,zhiwei=?,password=? where clerkid=?";
    		
    		return this.upDate(sql, params);
    }
    
    /**
     * 删除指定的登录用户
     * @param params
     * @return
     */
    public boolean deleteLoginInfo(String[] params) {
    		String sql = "delete from UserLogin where clerkid=?";
    		
    		return this.upDate(sql, params);
    }
    
    public void getLoginInfo() {
    		String sql = "select clerkid,name,zhiwei,password from UserLogin";
    		String[] params = {};	// 没有参数, 传递一个空值
    		
    		this.query(sql, params);
    }
}