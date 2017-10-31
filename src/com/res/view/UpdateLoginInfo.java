/**
 * 功能: 修改登录信息
 * 时间: 2013.04.30
 *
 */
package com.res.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.res.model.LoginModel;

public class UpdateLoginInfo extends JDialog implements ActionListener {

    //定义需要的swing组件
    JLabel jl1, jl2, jl3, jl4;
    JButton jb1, jb2;
    JTextField jtf1, jtf2, jtf3, jtf4;
    JPanel jp1, jp2, jp3;

    /**
     * 
     * @param owner owner是StuAddDialog的父窗口
     * @param title title是窗口的名字
     * @param modal modal指定窗口是模式的还是非模式的,就是此窗口出现时让不让进行其他操作
     * @param lm
     * @param rowNums
     */
    public UpdateLoginInfo(Frame owner, String title, boolean modal, LoginModel lm, int rowNums) {
        //调用父类构造方法，达到模式对话框效果
        super(owner, title, modal);
        jl1 = new JLabel("员工号", jl1.CENTER);
        jl2 = new JLabel("姓名", jl2.CENTER);
        jl3 = new JLabel("职位", jl3.CENTER);
        jl4 = new JLabel("密码", jl4.CENTER);

        jtf1 = new JTextField(30);
        //初始化数据
        jtf1.setText((String) lm.getValueAt(rowNums, 0));
        //jtf1主键不能被修改
        jtf1.setEditable(false);
        jtf2 = new JTextField(30);
        jtf2.setText((String) lm.getValueAt(rowNums, 1));
        jtf3 = new JTextField(30);
        jtf3.setText((String) lm.getValueAt(rowNums, 2));
        jtf4 = new JTextField(30);
        jtf4.setText((String) lm.getValueAt(rowNums, 3));//整数转换为字符串

        jb1 = new JButton("修改");
        jb1.addActionListener(this);
        jb2 = new JButton("取消");
        jb2.addActionListener(this);

        jp1 = new JPanel(new GridLayout(8, 1));
        jp2 = new JPanel(new GridLayout(8, 1));
        jp3 = new JPanel();

        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);

        jp2.add(jtf1);
        jp2.add(jtf2);
        jp2.add(jtf3);
        jp2.add(jtf4);

        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.EAST);
        this.add(jp3, BorderLayout.SOUTH);

        this.add(jp1);
        this.setLocation(400, 300);
        this.setSize(500, 400);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            String[] params = {jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf1.getText()};
            LoginModel loginModel = new LoginModel();
            if (loginModel.updateLoginInfo(params)) {
            		JOptionPane.showMessageDialog(this, "修改成功！");
            		this.dispose();
            } else {
            		JOptionPane.showMessageDialog(this, "修改失败！");
            }
        } else if (e.getSource() == jb2) {
            this.dispose();
        }
    }
}

