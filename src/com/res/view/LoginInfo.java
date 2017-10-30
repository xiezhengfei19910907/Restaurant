/**
 * 登录管理界面实现
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
        jla1 = new JLabel("请输入姓名(员工号或职位)");

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
        String paras[] = {"1"};
        lm.query("select  clerkid,name,zhiwei,password  from UserLogin  where 1=?", paras);
        jtable = new JTable(lm);

        p2 = new JPanel(new BorderLayout());
        jsp = new JScrollPane(jtable);
        p2.add(jsp);
        //设置下凹效果
        jsp.setBorder(BorderFactory.createLoweredBevelBorder());//此句话一定要在下面三句话的前面，否则框上面没有标题文字

        Border lineBorder = BorderFactory.createLoweredBevelBorder();
        jsp.setBorder(BorderFactory.createTitledBorder(lineBorder, "登录管理", TitledBorder.LEFT, TitledBorder.TOP));


        //处理南部的
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jla2 = new JLabel("总记录数**条");
        p3.add(jla2);

        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jb2 = new JButton("删除用户");
        jb2.addActionListener(this);

        //jtable.setModel(lm);
        //jb2.setText("总记录数 " + lm.getRowCount() + " 条");
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
        //this.setBackground(Color.pink);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == jb3) {///添加密码
            AddMima am = new AddMima(Windows1.w1, "添加密码", true);
            String[] paras = {"1"};
            //更新数据模型
            lm = new LoginModel();
            lm.query("select  clerkid,name,zhiwei,password  from UserLogin  where 1=?", paras);
            this.jtable.setModel(lm);
            jla2.setText("总记录数 " + lm.getRowCount() + " 条");
        } else if (e.getSource() == jb4) {////修改密码

            int i = this.jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行！");
                return;
            }
            lm = new LoginModel();
            String paras[] = {"1"};
            lm.query("select *  from UserLogin  where 1=?", paras);

            //更新信息输入完之前，数据模型已经更新了，因此
            UpdateMima updateMima = new UpdateMima(Windows1.w1, "修改密码", true, lm, i);
            //更新数据模型
            lm = new LoginModel();
            lm.query("select  clerkid,name,zhiwei,password  from UserLogin  where 1=?", paras);
            this.jtable.setModel(lm);
            jla2.setText("总记录数 " + lm.getRowCount() + " 条");
        } else if (e.getSource() == jb2) {  /////删除
            //String  i=new Integer(jtable.getSelectedRow()).toString();
            int i = jtable.getSelectedRow();
            if (i == -1) {
                //弹出提示框
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            //得到学生ID编号
            String emId = (String) this.lm.getValueAt(i, 0);

            //创建一个sql语句
            String sql = "delete  from UserLogin  where clerkid=?";
            String[] paras = {emId};
            lm = new LoginModel();
            if (lm.upDate(sql, paras)) {
                JOptionPane.showMessageDialog(null, "恭喜你删除成功!");
            } else {
                JOptionPane.showMessageDialog(null, "对不起!删除不成功!");
            }

            //构建行的数据模型类，并更新
            lm = new LoginModel();
            String[] paras1 = {"1"};
            lm.query("select  clerkid,name,zhiwei,password  from UserLogin  where 1=?", paras1);
            //更新JTable
            this.jtable.setModel(lm);//自动完成更新
            jla2.setText("总记录数 " + lm.getRowCount() + " 条");
        } else if (e.getSource() == jb1) {///////////////查询

            // “查询”按钮所触发事件的处理
            if (jtf.getText().trim().equals("")) {

                JOptionPane.showMessageDialog(null, "请输入要查询的内容");
            } else {
                St = jtf.getText().trim();
                String sql = "select  clerkid,name,zhiwei,password  from UserLogin  where clerkid=? or zhiwei=?";

                lm = new LoginModel();
                lm.queryStr(sql, St);
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
            //	jb2.setText("总记录数 " + lm.getRowCount() + " 条");
        } else if (e.getSource() == this.jb4) {
            this.jb4.setEnabled(true);
        } else if (e.getSource() == this.jb5) {
            this.jb5.setEnabled(true);
        }
    }

    public void mouseExited(MouseEvent e) {

    }
}
