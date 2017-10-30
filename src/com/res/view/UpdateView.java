/**
 * 人事管理界面的修改按钮，用于修改员工的信息
 */
package com.res.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import com.res.model.EmpModel;

public class UpdateView extends JDialog implements ActionListener {

    //定义需要的swing组件
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9;
    JButton jb1, jb2;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9;
    JPanel jp1, jp2, jp3;

    //owner是StuAddDialog的父窗口，
    //title是窗口的名字，
    //modal指定窗口是模式的还是非模式的,就是此窗口出现时让不让进行其他操作
    public UpdateView(Frame owner, String title, boolean modal, EmpModel em, int rowNums) {

        //调用父类构造方法，达到模式对话框效果
        super(owner, title, modal);
        jl1 = new JLabel("员工号", jl1.CENTER);
        jl2 = new JLabel("姓名", jl2.CENTER);
        jl3 = new JLabel("性别", jl3.CENTER);
        jl4 = new JLabel("籍贯", jl4.CENTER);
        jl5 = new JLabel("出生日期", jl5.CENTER);
        jl6 = new JLabel("证件号", jl6.CENTER);
        jl7 = new JLabel("学历", jl7.CENTER);
        jl8 = new JLabel("职位", jl8.CENTER);
        jl9 = new JLabel("婚否", jl9.CENTER);

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
        jtf4.setText((String) em.getValueAt(rowNums, 3));
        jtf5 = new JTextField(30);
        jtf5.setText((String) em.getValueAt(rowNums, 4));
        jtf6 = new JTextField(30);
        jtf6.setText((String) em.getValueAt(rowNums, 5));
        jtf7 = new JTextField(30);
        jtf7.setText((String) em.getValueAt(rowNums, 6));
        jtf8 = new JTextField(30);
        jtf8.setText((String) em.getValueAt(rowNums, 7));
        jtf9 = new JTextField(30);
        jtf9.setText((String) em.getValueAt(rowNums, 8));

        jb1 = new JButton("修改");
        jb1.addActionListener(this);
        jb2 = new JButton("取消");
        jb2.addActionListener(this);

        jp1 = new JPanel(new GridLayout(9, 1));
        jp2 = new JPanel(new GridLayout(9, 1));
        jp3 = new JPanel();

        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);
        jp1.add(jl5);
        jp1.add(jl6);
        jp1.add(jl7);
        jp1.add(jl8);
        jp1.add(jl9);

        jp2.add(jtf1);
        jp2.add(jtf2);
        jp2.add(jtf3);
        jp2.add(jtf4);
        jp2.add(jtf5);
        jp2.add(jtf6);
        jp2.add(jtf7);
        jp2.add(jtf8);
        jp2.add(jtf9);

        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.EAST);
        this.add(jp3, BorderLayout.SOUTH);

        this.add(jp1);
        this.setLocation(400, 300);
        this.setSize(600, 500);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//这句话不能有，否则会报警
        this.setVisible(true);//这句话必须有，否则不会显示出来
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            //做一个sql
            //sql语句创建
            /*String sql="update renshiziliao set clerkid=?,name=?,sex=?,address=?,birth=?,id=?,xueli=?,zhiwei=?,hunfou=? where clerkid=?";*/
            String sql = "update renshiziliao set name=?,sex=?,address=?,birth=?,id=?,xueli=?,zhiwei=?,hunfou=? where clerkid=? ";
            String[] paras = {jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(), jtf6.getText(), jtf7.getText(), jtf8.getText(), jtf9.getText(), jtf1.getText()};
            EmpModel tempModel = new EmpModel();
            tempModel.upDate(sql, paras);
            this.dispose();
        } else if (e.getSource() == jb2) {
            this.dispose();
        }
    }
}