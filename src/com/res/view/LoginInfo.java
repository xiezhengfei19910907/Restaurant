/**
 * 功能: 登录管理界面实现
 * 时间: 2013.04.30
 */
package com.res.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.res.model.LoginModel;
import com.res.mytools.*;

public class LoginInfo extends JPanel implements ActionListener, MouseListener {
    //定义需要的各个组件
    JPanel p1, p2, p3, p4, p5;
    JLabel jla1, jla2;
    JTextField jtf;
    JButton jb1, jb2, jb3, jb4, jb5;
    //显示认识信息的表格
    JTable jtable;
    JScrollPane jsp;
    LoginModel lm;
    boolean b = false;
    String St = null;

    public LoginInfo() {
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));//流布局的居中布局
        jla1 = new JLabel("请输入姓名, 员工号或者职位");

        jla1.setFont(MyFont.f2);
        jtf = new JTextField(20);
        jb1 = new JButton("查询");
        jb1.setFont(MyFont.f2);
        jb1.addActionListener(this);
        //把上面加入到p1
        p1.add(jla1);
        p1.add(jtf);
        p1.add(jb1);

        //处理中间的人事表格
        lm = new LoginModel();
        lm.getLoginInfo();
        jtable = new JTable(lm);

        p2 = new JPanel(new BorderLayout());
        jsp = new JScrollPane(jtable);
        p2.add(jsp);
        //设置下凹效果
        jsp.setBorder(BorderFactory.createLoweredBevelBorder());

        Border lineBorder = BorderFactory.createLoweredBevelBorder();
        jsp.setBorder(BorderFactory.createTitledBorder(lineBorder, "登录管理", TitledBorder.LEFT, TitledBorder.TOP));

        //处理南部的
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jla2 = new JLabel("总记录数" + lm.getRowCount() + "条");
        p3.add(jla2);

        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jb2 = new JButton("删除用户");
        jb2.addActionListener(this);

        jb3 = new JButton("添加用户");
        jb3.addActionListener(this);

        jb4 = new JButton("修改用户");
        jb4.addActionListener(this);
        p4.add(jb2);
        p4.add(jb3);
        p4.add(jb4);
        p5 = new JPanel(new BorderLayout());
        p5.add(p3, BorderLayout.WEST);
        p5.add(p4, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.add(p5, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb3) {	// 添加登录用户, 打开新增登录用户弹框
            new AddLoginInfo(Windows1.w1, "添加密码", true);
            
            // 更新表格数据
            this.refreshTable();
        } else if (e.getSource() == jb4) {	// 修改登录用户信息, 打开修改登录用户弹框
            int i = this.jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行！");
                return;
            }
            new UpdateLoginInfo(Windows1.w1, "修改密码", true, lm, i);
            
            // 更新表格数据
            this.refreshTable();
        } else if (e.getSource() == jb2) {	// 删除登录用户
            int i = jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            //得到员工号
            String emId = (String) this.lm.getValueAt(i, 0);

            String[] params = {emId};
            lm = new LoginModel();
            if (lm.deleteLoginInfo(params)) {
                JOptionPane.showMessageDialog(null, "删除成功!");
            } else {
                JOptionPane.showMessageDialog(null, "删除失败!");
            }

            // 更新表格数据
            this.refreshTable();
        } else if (e.getSource() == jb1) {	// 查询
            if (jtf.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入要查询的内容");
            } else {
                St = jtf.getText().trim();
                lm = new LoginModel();
                lm.getLoginInfoByCondition(St);
                this.jtable.setModel(lm);

                jla2.setText("总记录数 " + lm.getRowCount() + " 条");
            }
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        int i = jtable.getSelectedRow();
        if (i != 0) {
            this.jb2.setEnabled(true);
        } else if (e.getSource() == this.jb4) {
            this.jb4.setEnabled(true);
        } else if (e.getSource() == this.jb5) {
            this.jb5.setEnabled(true);
        }
    }

    public void mouseExited(MouseEvent e) {

    }
    
    /**
     * 更新表格
     */
    public void refreshTable() {
    		lm = new LoginModel();
        lm.getLoginInfo();
        this.jtable.setModel(lm);
        jla2.setText("总记录数 " + lm.getRowCount() + " 条");
    }
}