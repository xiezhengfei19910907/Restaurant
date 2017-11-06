/**
 * 功能：软件工程实验闪屏界面
 * 时间：2013.04.30
 */
package com.res.view;

import javax.swing.*;
import java.awt.*;

public class Index extends JWindow implements Runnable {
    paint p;

    public static void main(String[] args) {
        Index index = new Index();
        Thread t = new Thread(index);
        t.start();
    }

    public Index() {
        p = new paint();
        this.add(p);
        this.setSize(400, 260);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 - 200, height / 2 - 150);
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(32 * 380);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Login();
            this.dispose();
            break;
        }
    }
}

class paint extends JPanel implements Runnable {
    Thread t;
    int x = 10;
    int i = 0, j = 40, u = 10;
    String gg[] = {"系", "统", "正", "在", "加", "载", "请", "稍", "候"};
    int k = 0, tt = 0;
    String shi[] = {"软", "件", "工", "程", "试", "验", "文", "档", "卜", "令", "干", " ", "柴", "化", "然", "董", "立", "柱", " ", "赵", "明", "亮", "编", "码", "丁", "春", " ", "王", "超", " ", "谢", "正", "飞"};

    boolean ifok = true;
    int width = 180;
    int height = 0;
    int dian = 0;

    paint() {
        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (true) {
            if (x <= 380) repaint();
            try {
                Thread.sleep(70);
                i++;
                j = j - 6;
                u = u + 10;

                if (tt == 6) width = width - 20;

                if (i == 4) {
                    tt++;
                    if (ifok && x > 120 + k * 20) k++;
                    if (k >= gg.length - 1) ifok = false;
                    x = x + 10;
                    i = 0;
                    j = 40;
                    u = 10;
                    dian++;
                    if (dian > 3) dian = 0;
                }
            } catch (InterruptedException e) {
                System.out.println("线程中断");
            }
        }
    }

    public void paintComponent(Graphics g) {
        Image image;
        image = Toolkit.getDefaultToolkit().getImage("image/index/index.jpg");
        g.drawImage(image, 0, 0, this.getWidth(), 200, this);

        int r = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        int y = (int) (Math.random() * 255);

        g.setColor(new Color(253, 250, 250));
        g.fillRect(x, 210, 390 - x, 30);
        g.setColor(new Color(253, 250, 250));
        if (i > 1) g.fillRect(x, 255 - (j + 20) / 2, 10, j + 20);
        if (j > 25) g.setColor(new Color(r, b, y));
        else g.setColor(new Color(193, 194, 252));
        g.fillRect(x, 255 - j / 2, 10, j);
        g.setColor(new Color(123, 194, 252));
        g.drawRect(10, 210, 380, 30);

        if (x < 120) {
            for (int l = 0; l < gg.length; l++) {
                g.setColor(new Color(0, 0, 0));
                g.drawString(gg[l], 120 + l * 20, 230);
            }

            for (int l = 0; l < dian; l++) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("*", 300 + l * 10, 235);
            }
            g.drawString("*", 300 + 10 * dian, 235);
        } else {
            g.setColor(new Color(23, 23, 230));
            g.drawString(gg[k], 120 + k * 20, 230);

            for (int l = k + 1; l < gg.length; l++) {
                g.setColor(new Color(0, 0, 0));
                g.drawString(gg[l], 120 + l * 20, 230);
            }
            if (x > 300 + dian * 10) g.setColor(new Color(23, 23, 230));


            for (int l = 0; l < dian; l++) {
                g.drawString("*", 300 + l * 10, 235);
            }
            g.drawString("*", 300 + 10 * dian, 235);
        }

        //逐行写字
        if (tt < 6) {
            for (int rr = 0; rr < tt; rr++) {
                g.setColor(new Color(r, b, y));
                g.drawString(shi[tt], 180, 40 + tt * 20);
            }
            g.drawString(shi[tt], 180, 40 + tt * 20);
        }

        if (tt >= 6 && tt < 8) {
            g.setColor(new Color(230, 0, 0));
            for (int rr = 0; rr < 6; rr++) {
                g.drawString(shi[rr], 180, 40 + rr * 20);
            }
            g.setColor(new Color(r, b, y));
            if (tt < 8) {
                for (int rr = 6; rr < tt; rr++) {
                    g.drawString(shi[rr], 150, rr * 20 - 60);
                }
            }
            if (tt >= 8) {
                for (int rr = 6; rr <= 7; rr++) {
                    g.drawString(shi[rr], 150, rr * 20 - 60);
                }
            }
        }

        if (tt >= 8 && tt < 15) {
            g.setColor(new Color(230, 0, 0));
            for (int rr = 0; rr < 6; rr++) {
                g.drawString(shi[rr], 180, 40 + rr * 20);
            }
            for (int rr = 6; rr <= 7; rr++) {
                g.drawString(shi[rr], 150, rr * 20 - 60);
            }
            g.setColor(new Color(r, b, y));
            if (tt < 15) {
                for (int rr = 8; rr < tt; rr++) {
                    g.drawString(shi[rr], 120, rr * 20 - 120);
                }
            }
            if (tt >= 15) {
                for (int rr = 8; rr <= 14; rr++) {
                    g.drawString(shi[rr], 120, rr * 20 - 120);
                }
            }
        }

        if (tt >= 15 && tt < 22) {
            g.setColor(new Color(230, 0, 0));
            for (int rr = 0; rr < 6; rr++) {
                g.drawString(shi[rr], 180, 40 + rr * 20);
            }
            for (int rr = 6; rr <= 7; rr++) {
                g.drawString(shi[rr], 150, rr * 20 - 60);
            }
            for (int rr = 8; rr < 15; rr++) {
                g.drawString(shi[rr], 120, rr * 20 - 120);
            }
            g.setColor(new Color(r, b, y));
            if (tt < 22) {
                for (int rr = 15; rr <= tt; rr++) {
                    g.drawString(shi[rr], 90, rr * 20 - 260);
                }
            }
            if (tt >= 22) {
                for (int rr = 15; rr <= 21; rr++) {
                    g.drawString(shi[rr], 90, rr * 20 - 260);
                }
            }
        }

        if (tt >= 22 && tt < 24) {
            g.setColor(new Color(230, 0, 0));
            for (int rr = 0; rr < 6; rr++) {
                g.drawString(shi[rr], 180, 40 + rr * 20);
            }
            for (int rr = 6; rr <= 7; rr++) {
                g.drawString(shi[rr], 150, rr * 20 - 60);
            }
            for (int rr = 8; rr < 15; rr++) {
                g.drawString(shi[rr], 120, rr * 20 - 120);
            }
            for (int rr = 15; rr < 22; rr++) {
                g.drawString(shi[rr], 90, rr * 20 - 260);
            }
            g.setColor(new Color(r, b, y));
            if (tt < 24) {
                for (int rr = 22; rr <= tt; rr++) {
                    g.drawString(shi[rr], 60, rr * 20 - 380);
                }
            }
            if (tt >= 24) {
                for (int rr = 22; rr <= 23; rr++) {
                    g.drawString(shi[rr], 60, rr * 2 - 380);
                }
            }
        }

        if (tt >= 24 && tt < 33) {
            g.setColor(new Color(230, 0, 0));
            for (int rr = 0; rr < 6; rr++) {
                g.drawString(shi[rr], 180, 40 + rr * 20);
            }
            for (int rr = 6; rr <= 7; rr++) {
                g.drawString(shi[rr], 150, rr * 20 - 60);
            }
            for (int rr = 8; rr < 15; rr++) {
                g.drawString(shi[rr], 120, rr * 20 - 120);
            }
            for (int rr = 15; rr < 22; rr++) {
                g.drawString(shi[rr], 90, rr * 20 - 260);
            }
            for (int rr = 22; rr < 24; rr++) {
                g.drawString(shi[rr], 60, rr * 20 - 380);
            }
            g.setColor(new Color(r, b, y));
            if (tt < 33) {
                for (int rr = 24; rr <= tt; rr++) {
                    g.drawString(shi[rr], 30, rr * 20 - 440);
                }
            }
            if (tt >= 33) {
                for (int rr = 24; rr <= 32; rr++) {
                    g.drawString(shi[rr], 30, rr * 20 - 440);
                }
            }
        }

        if (tt >= 33) {
            g.setColor(new Color(230, 0, 0));
            for (int rr = 0; rr < 6; rr++) {
                g.drawString(shi[rr], 180, 40 + rr * 20);
            }
            for (int rr = 6; rr <= 7; rr++) {
                g.drawString(shi[rr], 150, rr * 20 - 60);
            }
            for (int rr = 8; rr < 15; rr++) {
                g.drawString(shi[rr], 120, rr * 20 - 120);
            }
            for (int rr = 15; rr < 22; rr++) {
                g.drawString(shi[rr], 90, rr * 20 - 260);
            }
            for (int rr = 22; rr < 24; rr++) {
                g.drawString(shi[rr], 60, rr * 20 - 380);
            }
            for (int rr = 24; rr < 33; rr++) {
                g.drawString(shi[rr], 30, rr * 20 - 440);
            }
            g.setColor(new Color(r, b, y));
        }
    }
}