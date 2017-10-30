/*
 * 修改菜谱信息
 */

package com.res.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.beans.Statement;
import java.sql.*;

import javax.swing.*;

import com.res.model.EmpModel;
import com.res.model.MenuModel;


//import com.sun.corba.se.pept.transport.Connection;在连接数据库的时候这个包很容易自动被引入，这个包不对

public class UpdateMenu extends JDialog implements ActionListener {

    //定义需要的swing组件
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
    JButton jb1, jb2;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7;
    JPanel jp1, jp2, jp3;

    //owner是StuAddDialog的父窗口，
    //title是窗口的名字，
    //modal指定窗口是模式的还是非模式的,就是此窗口出现时让不让进行其他操作
    public UpdateMenu(Frame owner, String title, boolean modal, MenuModel em, int rowNums) {

        //调用父类构造方法，达到模式对话框效果
        super(owner, title, modal);
        jl1 = new JLabel("编号", jl1.CENTER);
        jl2 = new JLabel("名称", jl2.CENTER);
        jl3 = new JLabel("类别", jl3.CENTER);
        jl4 = new JLabel("价格", jl4.CENTER);
        jl5 = new JLabel("原料", jl5.CENTER);
        jl6 = new JLabel("备注", jl6.CENTER);
        jl7 = new JLabel("折扣", jl7.CENTER);

        jtf1 = new JTextField(30);
        //初始化数据
        jtf1.setText((String) em.getValueAt(rowNums, 0));
        //jtf1主键不能被修改
        jtf1.setEditable(false);
        jtf2 = new JTextField(30);
        jtf2.setText((String) em.getValueAt(rowNums, 1));
        jtf3 = new JTextField(30);
        jtf3.setText((String) em.getValueAt(rowNums, 2));
        jtf4 = new JTextField(30);
        jtf4.setText((String) em.getValueAt(rowNums, 3));//整数转换为字符串
        jtf5 = new JTextField(30);
        jtf5.setText((String) em.getValueAt(rowNums, 4));
        jtf6 = new JTextField(30);
        jtf6.setText((String) em.getValueAt(rowNums, 5));
        jtf7 = new JTextField(30);
        jtf7.setText((String) em.getValueAt(rowNums, 6));

        jb1 = new JButton("修改");
        jb1.addActionListener(this);
        jb2 = new JButton("取消");
        jb2.addActionListener(this);

        jp1 = new JPanel(new GridLayout(7, 1));
        jp2 = new JPanel(new GridLayout(7, 1));
        jp3 = new JPanel();

        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);
        jp1.add(jl5);
        jp1.add(jl6);
        jp1.add(jl7);


        jp2.add(jtf1);
        jp2.add(jtf2);
        jp2.add(jtf3);
        jp2.add(jtf4);
        jp2.add(jtf5);
        jp2.add(jtf6);
        jp2.add(jtf7);


        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.EAST);
        this.add(jp3, BorderLayout.SOUTH);


        this.add(jp1);
        this.setLocation(400, 300);
        this.setSize(500, 400);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//这句话不能有，否则会报警
        this.setVisible(true);//这句话必须有，否则不会显示出来
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            //做一个sql
            //sql语句创建

            String sql = "update menu set 名称=?,类别=?,价格=?,原料=?,备注=?,折扣=? where 编号=?";
            //	String sql="update 人事资料 set 姓名=?,性别=?,职位=?,籍贯=?,学历=?,出生日期=?,婚否=? where 员工号=?";
            String[] paras = {jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(), jtf6.getText(), jtf7.getText(), jtf1.getText()};
            MenuModel tempModel = new MenuModel();
            tempModel.upDate(sql, paras);
            this.dispose();
        } else if (e.getSource() == jb2) {
            this.dispose();
        }

    }
}

