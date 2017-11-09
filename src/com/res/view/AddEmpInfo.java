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
import javax.swing.SwingConstants;

import com.res.model.EmpModel;

/**
 * 人事资料中的添加用户
 * @author allen
 *
 */
public class AddEmpInfo extends JDialog implements ActionListener {

    /**
     * 定义需要的swing组件
     */
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9;
    JButton jb1, jb2;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9;
    JPanel jp1, jp2, jp3;

    /**
     * 
     * @param owner owner是StuAddDialog的父窗口
     * @param title title是窗口的名字
     * @param modal modal指定窗口是模式的还是非模式的,就是此窗口出现时让不让进行其他操作
     */
    public AddEmpInfo(Frame owner, String title, boolean modal) {
        //调用父类构造方法，达到模式对话框效果
        super(owner, title, modal);
        jl1 = new JLabel("员工号", SwingConstants.CENTER);
        jl2 = new JLabel("姓名", SwingConstants.CENTER);
        jl3 = new JLabel("性别", SwingConstants.CENTER);
        jl4 = new JLabel("籍贯", SwingConstants.CENTER);
        jl5 = new JLabel("出生日期", SwingConstants.CENTER);
        jl6 = new JLabel("证件号", SwingConstants.CENTER);
        jl7 = new JLabel("学历", SwingConstants.CENTER);
        jl8 = new JLabel("职位", SwingConstants.CENTER);
        jl9 = new JLabel("婚否", SwingConstants.CENTER);

        jtf1 = new JTextField(30);
        jtf2 = new JTextField(30);
        jtf3 = new JTextField(30);
        jtf4 = new JTextField(30);
        jtf5 = new JTextField(30);
        jtf6 = new JTextField(30);
        jtf7 = new JTextField(30);
        jtf8 = new JTextField(30);
        jtf9 = new JTextField(30);

        jb1 = new JButton("添加");
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
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {	// 添加
            EmpModel empModel = new EmpModel();
            String params[] = {jtf1.getText(), jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(), jtf6.getText(), jtf7.getText(), jtf8.getText(), jtf9.getText()};
            if (empModel.insertEmpInfo(params)) {
                JOptionPane.showMessageDialog(this, "添加成功！");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "添加失败！");
            }
        } else if (e.getSource() == jb2) {	//  取消
            this.dispose();
        }
    }
}