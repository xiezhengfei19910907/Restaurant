package com.res.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.res.model.LoginModel;
import com.res.mytools.MyFont;

/**
 * 登录管理界面实现
 * @author allen
 *
 */
public class LoginInfo extends JPanel implements ActionListener, MouseListener {
    //定义需要的各个组件
    JPanel p1, p2, p3, p4, p5;
    JLabel jla1, jla2;
    JTextField jtf;
    JButton jb1, jb2, jb3, jb4, jb5;
    //显示认识信息的表格
    JTable jtable;
    JScrollPane jsp;
    LoginModel loginModel;

    public LoginInfo() {
    		this.p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));//流布局的居中布局
    		this.jla1 = new JLabel("请输入姓名, 员工号或者职位");

        this.jla1.setFont(MyFont.f2);
        this.jtf = new JTextField(20);
        this.jb1 = new JButton("查询");
        this.jb1.setFont(MyFont.f2);
        this.jb1.addActionListener(this);
        //把上面加入到p1
        this.p1.add(jla1);
        this.p1.add(jtf);
        this.p1.add(jb1);

        //处理中间的人事表格
        this.loginModel = new LoginModel();
        this.loginModel.getLoginInfo();
        this.jtable = new JTable(loginModel);

        this.p2 = new JPanel(new BorderLayout());
        this.jsp = new JScrollPane(jtable);
        this.p2.add(jsp);
        //设置下凹效果
        this.jsp.setBorder(BorderFactory.createLoweredBevelBorder());

        Border lineBorder = BorderFactory.createLoweredBevelBorder();
        this.jsp.setBorder(BorderFactory.createTitledBorder(lineBorder, "登录管理", TitledBorder.LEFT, TitledBorder.TOP));

        //处理南部的
        this.p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.jla2 = new JLabel("总记录数" + loginModel.getRowCount() + "条");
        this.p3.add(jla2);

        this.p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.jb2 = new JButton("删除用户");
        this.jb2.addActionListener(this);

        this.jb3 = new JButton("添加用户");
        this.jb3.addActionListener(this);

        this.jb4 = new JButton("修改用户");
        this.jb4.addActionListener(this);
        this.p4.add(jb2);
        this.p4.add(jb3);
        this.p4.add(jb4);
        this.p5 = new JPanel(new BorderLayout());
        this.p5.add(p3, BorderLayout.WEST);
        this.p5.add(p4, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.add(p5, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb3) {	// 添加登录用户, 打开新增登录用户弹框
            new AddLoginInfo(Windows.w1, "添加密码", true);
            
            // 更新表格数据
            this.refreshTable();
        } else if (e.getSource() == jb4) {	// 修改登录用户信息, 打开修改登录用户弹框
            int i = this.jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行！");
                return;
            }
            new UpdateLoginInfo(Windows.w1, "修改密码", true, loginModel, i);
            
            // 更新表格数据
            this.refreshTable();
        } else if (e.getSource() == jb2) {	// 删除登录用户
            int i = jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            //得到员工号
            String emId = (String) this.loginModel.getValueAt(i, 0);

            String[] params = {emId};
            this.loginModel = new LoginModel();
            if (this.loginModel.deleteLoginInfo(params)) {
                JOptionPane.showMessageDialog(null, "删除成功!");
            } else {
                JOptionPane.showMessageDialog(null, "删除失败!");
            }

            // 更新表格数据
            this.refreshTable();
        } else if (e.getSource() == jb1) {	// 查询
        		String Str = this.jtf.getText().trim();
            if (Str.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入要查询的内容");
                
                this.refreshTable();
            } else {
                this.loginModel = new LoginModel();
                this.loginModel.getLoginInfoByCondition(Str);
                this.jtable.setModel(loginModel);

                jla2.setText("总记录数 " + loginModel.getRowCount() + " 条");
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

    }

    public void mouseExited(MouseEvent e) {

    }
    
    /**
     * 更新表格
     */
    public void refreshTable() {
    		this.loginModel = new LoginModel();
        this.loginModel.getLoginInfo();
        this.jtable.setModel(loginModel);
        this.jla2.setText("总记录数 " + loginModel.getRowCount() + " 条");
    }
}