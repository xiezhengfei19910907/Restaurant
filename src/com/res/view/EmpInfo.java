/**
 * 人事管理界面
 */
package com.res.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.res.model.EmpModel;
import com.res.mytools.*;

public class EmpInfo extends JPanel implements ActionListener, MouseListener {

    //定义需要的各个组件
    JPanel p1, p2, p3, p4, p5;
    JLabel p1_lab1, p3_lab1;
    JTextField p1_jtf1;
    JButton p1_jb1, p1_jb2, p1_jb3, p1_jb4, p1_jb5;//,p1_jb6
    //用于显示人事信息
    JTable jtable;
    //显示认识信息的表格
    // static JTable jtable;
    JScrollPane jsp;
    EmpModel em;
    //ManageEmp mp;
    boolean b = false;

    //存放查询文本框的文本
    String Str = null;

    //构造函数
    public EmpInfo() {
        //处理北面
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));//流布局的居中布局
        p1_lab1 = new JLabel("请输入姓名(员工号、姓名或职位)");
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
        em = new EmpModel();
        String[] paras = {"1"};
        em.query("select  clerkid,name,sex,zhiwei  from renshiziliao  where 1=?", paras);

        jtable = new JTable(em);
        p2 = new JPanel(new BorderLayout());
        jsp = new JScrollPane(jtable);
        p2.add(jsp);
        //设置下凹效果
        jtable.setBorder(BorderFactory.createLoweredBevelBorder());//此句话一定要在下面三句话的前面，否则框上面没有标题文字
        Border lineBorder = BorderFactory.createLoweredBevelBorder();
        jsp.setBorder(BorderFactory.createTitledBorder(lineBorder, "人事管理", TitledBorder.LEFT, TitledBorder.TOP));

        //处理南部的
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3_lab1 = new JLabel("总记录数是**条");
        p3_lab1.setFont(MyFont.f3);
        p3.add(p3_lab1);

        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p1_jb2 = new JButton("详细信息");
        p1_jb2.setFont(MyFont.f3);
        p1_jb2.addActionListener(this);
        p1_jb3 = new JButton("添加");
        p1_jb3.setFont(MyFont.f3);
        p1_jb3.addActionListener(this);
        p1_jb4 = new JButton("修改");
        p1_jb4.setFont(MyFont.f3);
        p1_jb4.addActionListener(this);
        p1_jb5 = new JButton("删除");
        p1_jb5.setFont(MyFont.f3);
        p1_jb5.addActionListener(this);
        //p1_jb6=new JButton("显示全部员工");
        //p1_jb6.setFont(MyTools.f3);
        //p1_jb6.addActionListener(this);

        p4.add(p1_jb2);
        p4.add(p1_jb3);
        p4.add(p1_jb4);
        p4.add(p1_jb5);
        //p4.add(p1_jb6);
        p5 = new JPanel(new BorderLayout());
        p5.add(p3, BorderLayout.WEST);
        p5.add(p4, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(p1, "North");
        this.add(p2, "Center");
        this.add(p5, "South");
        //this.setBackground(Color.pink);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == p1_jb1) {		/*窗口北部，点击查询按钮所触发的事件*/
            if (p1_jtf1.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入要查询的内容");
            } else {
                Str = p1_jtf1.getText().trim();
                String sql = "select clerkid,name,sex,address,birth,id,xueli,zhiwei,hunfou  from renshiziliao where clerkid=? or name=? or zhiwei=? ";
                em = new EmpModel();
                em.queryStr(sql, Str);
                this.jtable.setModel(em);
                p3_lab1.setText("总记录数 " + em.getRowCount() + " 条");
            }
        } else if (e.getSource() == p1_jb2) {		/*窗口南部，点击详细按钮所触发的事件*/
            int i = jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行进行此操作！");
            }
            String empNo = (String) em.getValueAt(i, 0);
            String[] paras = {empNo};

            //System.out.println("row"+empNo);
            em = new EmpModel();
            em.query("select *  from renshiziliao where clerkid=?", paras);
            this.jtable.setModel(em);
            p3_lab1.setText("总记录数 " + em.getRowCount() + " 条");
        } else if (e.getSource() == p1_jb3) {	/*窗口南部，点击添加按钮所触发的事件*/
            AddDialog ae = new AddDialog(Windows1.w1, "添加", true);
            String[] paras = {"1"};

            //更新数据模型
            em = new EmpModel();
            em.query("select clerkid,name,sex,zhiwei from renshiziliao where 1=?", paras);
            this.jtable.setModel(em);
            p3_lab1.setText("总记录数 " + em.getRowCount() + " 条");
        } else if (e.getSource() == p1_jb4) {	/*窗口南部，点击修改按钮所触发的事件*/
            int i = this.jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行！");
                return;
            }
            em = new EmpModel();
            String paras[] = {"1"};
            em.query("select *  from renshiziliao  where 1=?", paras);

            //注意JDialog弹出来的时间单独的运行，下面的语句会执行不会等待uv这个类执行完再执行em的更新，因此
            //更新信息输入完之前，数据模型已经更新了，因此
            UpdateView updateView = new UpdateView(Windows1.w1, "修改信息", true, em, i);
            //更新数据模型
            em = new EmpModel();
            em.query("select clerkid,name,sex,zhiwei from renshiziliao  where 1=?", paras);
            this.jtable.setModel(em);
            p3_lab1.setText("总记录数 " + em.getRowCount() + " 条");
        } else if (e.getSource() == p1_jb5) {	 /*窗口南部，点击删除按钮所触发的事件*/
            //String  i=new Integer(jtable.getSelectedRow()).toString();
            int i = jtable.getSelectedRow();
            if (i == -1) {   	/* 一行都没选中，则提示选中一行*/
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            //得到学生ID编号
            String emId = (String) this.em.getValueAt(i, 0);

            //创建一个sql语句
            String sql = "delete  from renshiziliao where clerkid=?";
            String[] paras = {emId};
            em = new EmpModel();
            if (em.upDate(sql, paras)) {
                JOptionPane.showMessageDialog(null, "恭喜你删除成功!");
            } else {
                JOptionPane.showMessageDialog(null, "对不起!删除不成功!");
            }

            //构建行的数据模型类，并更新
            em = new EmpModel();
            String[] paras1 = {"1"};
            em.query("select clerkid,name,sex,zhiwei from renshiziliao where 1=?", paras1);
            //更新JTable
            this.jtable.setModel(em);//自动完成更新
            p3_lab1.setText("总记录数 " + em.getRowCount() + " 条");
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        /*int i=jtable.getSelectedRow();
		if(i!=0){
			this.p1_jb2.setEnabled(true);
		}else if(e.getSource()==this.p1_jb4){
			this.p1_jb4.setEnabled(true);
		}else if(e.getSource()==this.p1_jb5){
			this.p1_jb5.setEnabled(true);
		}*/
    }

    public void mouseExited(MouseEvent e) {

    }
}