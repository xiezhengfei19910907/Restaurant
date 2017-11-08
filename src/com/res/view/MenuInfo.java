/**
 * 菜谱价格管理
 */
package com.res.view;

import java.awt.BorderLayout;
import java.awt.Color;
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

import com.res.model.MenuModel;
import com.res.mytools.MyFont;

public class MenuInfo extends JPanel implements ActionListener, MouseListener {
    //定义需要的各个组件
    JPanel p1, p2, p3, p4, p5;
    JLabel jla1, jla2;
    JTextField jtf;
    JButton jb1, jb3, jb4, jb5;
    //显示认识信息的表格
    JTable jtable;
    JScrollPane jsp;
    MenuModel menuModel;
    boolean b = false;
    String Str = null;

    public MenuInfo() {
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));//流布局的居中布局
        jla1 = new JLabel("请输入缩写, 名称或分类");
        jla1.setFont(MyFont.f2);
        jtf = new JTextField(20);

        jb1 = new JButton("查询");
        jb1.addActionListener(this);
        jb1.setFont(MyFont.f2);
        //把上面加入到p1
        p1.add(jla1);
        p1.add(jtf);
        p1.add(jb1);

        //处理中间的表格
        menuModel = new MenuModel();
        menuModel.getMenuInfo();
        jtable = new JTable(menuModel);
        p2 = new JPanel(new BorderLayout());
        jsp = new JScrollPane(jtable);
        p2.add(jsp);
        //设置下凹效果
        jsp.setBorder(BorderFactory.createLoweredBevelBorder());

        Border lineBorder = BorderFactory.createLoweredBevelBorder();
        jsp.setBorder(BorderFactory.createTitledBorder(lineBorder, "菜单服务", TitledBorder.LEFT, TitledBorder.TOP));

        //处理南部的
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jla2 = new JLabel("共有" + menuModel.getRowCount() + "条记录");
        p3.add(jla2);

        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jb3 = new JButton("添加");
        jb3.addActionListener(this);
        jb4 = new JButton("修改");
        jb4.addActionListener(this);
        jb5 = new JButton("删除");
        jb5.addActionListener(this);
        p4.add(jb3);
        p4.add(jb4);
        p4.add(jb5);
        p5 = new JPanel(new BorderLayout());
        p5.add(p3, BorderLayout.WEST);
        p5.add(p4, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.add(p5, BorderLayout.SOUTH);
        this.setBackground(Color.pink);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {	// 查询
            if (jtf.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入要查询的内容");
                
                this.refreshTable();
            } else {
                Str = jtf.getText().trim();
                menuModel = new MenuModel();
                menuModel.getMenuInfoByCondition(Str);
                this.jtable.setModel(menuModel);
                jla2.setText("总记录数 " + menuModel.getRowCount() + " 条");
            }
        } else if (e.getSource() == jb3) {	// 添加
            new AddMenuInfo(Windows.w1, "添加", true);

            //更新数据
            this.refreshTable();
        } else if (e.getSource() == jb4) {	//修改
            int i = this.jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行！");
                return;
            }
            new UpdateMenuInfo(Windows.w1, "修改信息", true, menuModel, i);
            
            //更新数据
            this.refreshTable();
        } else if (e.getSource() == jb5) {	// 删除
            int i = jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            // 获取员工编号
            String emId = (String) this.menuModel.getValueAt(i, 0);

            String[] params = {emId};
            menuModel = new MenuModel();
            if (menuModel.deleteMenuInfo(params)) {
                JOptionPane.showMessageDialog(null, "删除成功!");
            } else {
                JOptionPane.showMessageDialog(null, "删除失败!");
            }

            // 更新表格
            this.refreshTable();
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
    		menuModel = new MenuModel();
    		menuModel.getMenuInfo();
        this.jtable.setModel(menuModel);
        jla2.setText("总记录数 " + menuModel.getRowCount() + " 条");
    }
}