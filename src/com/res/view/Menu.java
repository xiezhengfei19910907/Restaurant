/**
 * 菜谱价格管理
 */
package com.res.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.res.model.*;
import com.res.mytools.*;

public class Menu extends JPanel implements ActionListener, MouseListener {
    //定义需要的各个组件
    JPanel p1, p2, p3, p4, p5;
    JLabel jla1, jla2;
    JTextField jtf;
    JButton jb1, jb2, jb3, jb4, jb5;
    //显示认识信息的表格
    JTable jtable;
    JScrollPane jsp;
    MenuModel mm;
    JComboBox jcb;
    boolean b = false;
    String Str2 = null;

    public Menu() {
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));//流布局的居中布局
        jla1 = new JLabel("请输入查询条件");
        jla1.setFont(MyFont.f2);
        jtf = new JTextField(20);
        jcb = new JComboBox();
        jcb.setEditable(true);
        jcb.addItem("川菜");
        jcb.addItem("浙菜");
        jcb.addItem("东北菜");
        jcb.addItem("沪菜");
        jcb.addItem("京菜");
        jcb.addItem("湘菜");

        jb1 = new JButton("查询");
        jb1.addActionListener(this);
        jb1.setFont(MyFont.f2);
        //把上面加入到p1
        p1.add(jla1);
        p1.add(jtf);
        p1.add(jcb);
        p1.add(jb1);

        //处理中间的人事表格
        mm = new MenuModel();
        String paras[] = {"1"};
        mm.query("select * from menu  where 1=?", paras);
        jtable = new JTable(mm);
        p2 = new JPanel(new BorderLayout());
        jsp = new JScrollPane(jtable);
        p2.add(jsp);
        //设置下凹效果
        jsp.setBorder(BorderFactory.createLoweredBevelBorder());//此句话一定要在下面三句话的前面，否则框上面没有标题文字

        Border lineBorder = BorderFactory.createLoweredBevelBorder();
        jsp.setBorder(BorderFactory.createTitledBorder(lineBorder, "菜谱价格",
                TitledBorder.LEFT, TitledBorder.TOP));

        //处理南部的
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jla2 = new JLabel("共有*条记录");
        p3.add(jla2);

        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jb2 = new JButton("显示详细信息");
        jb2.addActionListener(this);
        jb3 = new JButton("添加");
        jb3.addActionListener(this);
        jb4 = new JButton("修改");
        jb4.addActionListener(this);
        jb5 = new JButton("删除");
        jb5.addActionListener(this);
        p4.add(jb2);
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
        if (e.getSource() == jb1) {  /*窗口北部，点击查询按钮所触发的事件*/
            if (jtf.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入要查询的内容");
            } else {
                Str2 = jtf.getText().trim();
                String sql = "select * from menu where abbreviation=? or name=? or category=?";
                mm = new MenuModel();
                mm.queryStr(sql, Str2);
                this.jtable.setModel(mm);
                ///System.out.println("输出结果");
                jla2.setText("总记录数 " + mm.getRowCount() + " 条");
            }
        } else if (e.getSource() == jb2) {  /*窗口南部，点击详细按钮所触发的事件*/
            int i = jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行进行此操作！");
            }

            String eNo = (String) mm.getValueAt(i, 0);
            String[] paras = {eNo};

            System.out.println("row" + eNo);
            mm = new MenuModel();

            mm.query("select *  from menu   where abbreviation=?", paras);
            this.jtable.setModel(mm);
            jla2.setText("总记录数 " + mm.getRowCount() + " 条");
        } else if (e.getSource() == jb3) {     /*窗口南部，点击添加按钮所触发的事件*/
            AddMenu ae = new AddMenu(Windows1.w1, "添加", true);
            String[] paras = {"1"};

            //更新数据模型
            mm = new MenuModel();
            mm.query("select *  from menu  where  1=?", paras);
            this.jtable.setModel(mm);
            jla2.setText("总记录数 " + mm.getRowCount() + " 条");
        } else if (e.getSource() == jb4) {   /*窗口南部，点击修改按钮所触发的事件*/

            int i = this.jtable.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "请您选中一行！");
                return;
            }
            mm = new MenuModel();
            String paras[] = {"1"};
            mm.query("select *  from menu  where 1=?", paras);

            //注意JDialog弹出来的时间单独的运行，下面的语句会执行不会等待uv这个类执行完再执行em的更新，因此
            //更新信息输入完之前，数据模型已经更新了，因此
            UpdateMenu uv = new UpdateMenu(Windows1.w1, "修改信息", true, mm, i);
            //更新数据模型
            mm = new MenuModel();
            mm.query("select *   from menu  where 1=?", paras);
            this.jtable.setModel(mm);
            jla2.setText("总记录数 " + mm.getRowCount() + " 条");
        }/*else if(e.getSource()==jb4){/// /修改

			int i=this.jtable.getSelectedRow();
			if(i==-1){
				JOptionPane.showMessageDialog(this, "请您选中一行！");
			return;
			}
			mm=new  MenuModel();
			String paras[]={"1"};
			mm.query("select *  from menu where 1=? ", paras);
			//注意JDialog弹出来的时间单独的运行，下面的语句会执行不会等待um这个类执行完再执行em的更新，因此
			//更新信息输入完之前，数据模型已经更新了，因此
			UpdateMenu um=new UpdateMenu(Windows1.w1,"更新信息",true,mm,i);
			//更新数据模型
			mm=new MenuModel();
			mm.query("select *  from menu where 1=?", paras);
			this.jtable.setModel(mm);
			jla2.setText("总记录数 " + mm.getRowCount() + " 条");
		}*/ else if (e.getSource() == jb5) { /*窗口南部，点击删除按钮所触发的事件*/
            int i = jtable.getSelectedRow();
            if (i == -1) {
                //弹出提示框
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            //得到学生ID编号
            String emId = (String) this.mm.getValueAt(i, 0);

            //创建一个sql语句
            String sql = "delete  from menu  where abbreviation=?";
            String[] paras = {emId};
            mm = new MenuModel();
            if (mm.upDate(sql, paras)) {
                JOptionPane.showMessageDialog(null, "恭喜你删除成功!");
            } else {
                JOptionPane.showMessageDialog(null, "对不起!删除不成功!");
            }

            //构建行的数据模型类，并更新
            mm = new MenuModel();
            String[] paras1 = {"1"};
            mm.query("select *  from menu where  1=?", paras1);
            //更新JTable
            this.jtable.setModel(mm);//自动完成更新
            jla2.setText("总记录数 " + mm.getRowCount() + " 条");
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
}