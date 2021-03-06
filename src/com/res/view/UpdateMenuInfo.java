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

import com.res.model.MenuModel;

/**
 * 修改菜单信息
 * @author allen
 *
 */
public class UpdateMenuInfo extends JDialog implements ActionListener {

    /**
     * 定义需要的swing组件
     */
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
    JButton jb1, jb2;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7;
    JPanel jp1, jp2, jp3;

    /**
     * 
     * @param owner owner是StuAddDialog的父窗口
     * @param title title是窗口的名字
     * @param modal modal指定窗口是模式的还是非模式的,就是此窗口出现时让不让进行其他操作
     * @param em
     * @param rowNums
     */
    public UpdateMenuInfo(Frame owner, String title, boolean modal, MenuModel em, int rowNums) {
        //调用父类构造方法，达到模式对话框效果
        super(owner, title, modal);
        jl1 = new JLabel("编号", SwingConstants.CENTER);
        jl2 = new JLabel("名称", SwingConstants.CENTER);
        jl3 = new JLabel("类别", SwingConstants.CENTER);
        jl4 = new JLabel("价格", SwingConstants.CENTER);
        jl5 = new JLabel("原料", SwingConstants.CENTER);
        jl6 = new JLabel("备注", SwingConstants.CENTER);
        jl7 = new JLabel("折扣", SwingConstants.CENTER);

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
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {	// 修改
            String[] params = {jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(), jtf6.getText(), jtf7.getText(), jtf1.getText()};
            MenuModel menuModel = new MenuModel();
            if (menuModel.updateMenuInfo(params)) {
            		JOptionPane.showMessageDialog(this, "修改成功！");
            		this.dispose();
            } else {
            		JOptionPane.showMessageDialog(this, "修改失败！");
            }
        } else if (e.getSource() == jb2) {	// 取消
            this.dispose();
        }
    }
}

