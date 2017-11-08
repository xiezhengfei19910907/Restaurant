/**
 * 功能: 人事管理界面
 * 时间: 2013.04.30
 */
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

import com.res.model.EmpModel;
import com.res.mytools.MyFont;

public class EmpInfo extends JPanel implements ActionListener, MouseListener {

    //定义需要的各个组件
    JPanel p1, p2, p3, p4, p5;
    JLabel p1_lab1, p3_lab1;
    JTextField p1_jtf1;
    JButton p1_jb1, p1_jb3, p1_jb4, p1_jb5;
    //用于显示人事信息
    JTable jtable;
    //显示认识信息的表格
    JScrollPane jsp;
    EmpModel empModel;

    //存放查询文本框的文本
    String Str = null;

    //构造函数
    public EmpInfo() {
        //处理北面
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));//流布局的居中布局
        p1_lab1 = new JLabel("请输入员工号,姓名或职位");
        p1_lab1.setFont(MyFont.f2);
        p1_jtf1 = new JTextField(20);

        p1_jb1 = new JButton("查询");
        p1_jb1.setFont(MyFont.f3);
        p1_jb1.addActionListener(this);
        //把上面加入到p1
        p1.add(p1_lab1);
        p1.add(p1_jtf1);
        p1.add(p1_jb1);

        //处理中间的人事表格
        empModel = new EmpModel();
        empModel.getEmpInfo();

        jtable = new JTable(empModel);
        p2 = new JPanel(new BorderLayout());
        jsp = new JScrollPane(jtable);
        p2.add(jsp);
        //设置下凹效果
        jtable.setBorder(BorderFactory.createLoweredBevelBorder());
        Border lineBorder = BorderFactory.createLoweredBevelBorder();
        jsp.setBorder(BorderFactory.createTitledBorder(lineBorder, "人事管理", TitledBorder.LEFT, TitledBorder.TOP));

        //处理南部的
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3_lab1 = new JLabel("总记录数是" + empModel.getRowCount() + "条");
        p3_lab1.setFont(MyFont.f3);
        p3.add(p3_lab1);

        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p1_jb3 = new JButton("添加");
        p1_jb3.setFont(MyFont.f3);
        p1_jb3.addActionListener(this);
        p1_jb4 = new JButton("修改");
        p1_jb4.setFont(MyFont.f3);
        p1_jb4.addActionListener(this);
        p1_jb5 = new JButton("删除");
        p1_jb5.setFont(MyFont.f3);
        p1_jb5.addActionListener(this);

        p4.add(p1_jb3);
        p4.add(p1_jb4);
        p4.add(p1_jb5);
        p5 = new JPanel(new BorderLayout());
        p5.add(p3, BorderLayout.WEST);
        p5.add(p4, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(p1, "North");
        this.add(p2, "Center");
        this.add(p5, "South");
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == p1_jb1) {	// 查询
            if (p1_jtf1.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入要查询的内容");
                
                this.refreshTable();
            } else {
                Str = p1_jtf1.getText().trim();
                empModel = new EmpModel();
                empModel.getEmpInfoByCondition(Str);
                this.jtable.setModel(empModel);
                p3_lab1.setText("总记录数 " + empModel.getRowCount() + " 条");
            }
        } else if (e.getSource() == p1_jb3) {	// 添加
            new AddEmpInfo(Windows.w1, "添加", true);

            //更新数据
            this.refreshTable();
        } else if (e.getSource() == p1_jb4) {	// 修改
            int i = this.jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行！");
                return;
            }
            new UpdateEmpInfo(Windows.w1, "修改信息", true, empModel, i);
            
            //更新数据
            this.refreshTable();
        } else if (e.getSource() == p1_jb5) {	// 删除
            int i = jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            //获取员工号
            String emId = (String) this.empModel.getValueAt(i, 0);

            String[] params = {emId};
            empModel = new EmpModel();
            if (empModel.deleteEmpInfo(params)) {
                JOptionPane.showMessageDialog(null, "删除成功!");
            } else {
                JOptionPane.showMessageDialog(null, "删除失败!");
            }

            // 更新数据
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
    		empModel = new EmpModel();
        empModel.getEmpInfo();
        this.jtable.setModel(empModel);
        p3_lab1.setText("总记录数 " + empModel.getRowCount() + " 条");
    }
}