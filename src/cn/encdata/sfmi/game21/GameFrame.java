package cn.encdata.sfmi.game21;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JButton clear_btn = new JButton("洗牌");
    JButton compute_btn = new JButton("开始游戏");
    JButton game_btn = new JButton("玩家抓牌");
    JButton gameover_btn = new JButton("本轮结束");
    CardManager cm = new CardManager();
    JLabel game[] = new JLabel[52];  //放置52张扑克牌的标签
    int i = 0;  //定义抓牌数
    int countComputer = 0;  //存储电脑抓牌数量
    int computer_dot = 0;  //定义电脑点数
    int game_dot = 0;  //定义玩家点数
    JLabel computerCard[] = new JLabel[52];  //存储电脑抓的纸牌
    JLabel gameCard[] = new JLabel[52];  //存储玩家抓的纸牌
    int countGame = 0;//存储玩家抓牌数量
    JLabel jLabel1 = new JLabel("电脑显示牌区");
    JLabel jLabel2 = new JLabel("玩家显示牌区");

    public GameFrame() {
        getContentPane().setLayout(null);
        this.setTitle("二十一点游戏");
        this.setSize(800, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //获得屏幕的宽和高
        Dimension frameSize = this.getSize();  //获得当前窗体的宽和高
        //设置窗体居中
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        clear_btn.setBounds(new Rectangle(78, 388, 73, 31));
        clear_btn.addActionListener(this);
        compute_btn.setBounds(new Rectangle(233, 388, 86, 31));
        compute_btn.setEnabled(false);
        compute_btn.addActionListener(this);
        game_btn.setBounds(new Rectangle(413, 389, 91, 32));
        game_btn.setEnabled(false);
        game_btn.addActionListener(this);
        gameover_btn.setBounds(new Rectangle(625, 390, 91, 32));
        gameover_btn.setEnabled(false);
        gameover_btn.addActionListener(this);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("文件");
        JMenu help = new JMenu("帮助");
        JMenuItem fileExit = new JMenuItem("退出");
        JMenuItem helpAbout = new JMenuItem("关于");
        this.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(help);
        file.add(fileExit);
        fileExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        help.add(helpAbout);
        helpAbout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new AboutFrame();
            }
        });
        jLabel1.setBounds(new Rectangle(104, 343, 95, 38));
        jLabel2.setBounds(new Rectangle(499, 343, 92, 33));
        this.getContentPane().add(jLabel1);
        this.getContentPane().add(jLabel2);
        this.getContentPane().add(clear_btn);
        this.getContentPane().add(compute_btn);
        this.getContentPane().add(game_btn);
        this.getContentPane().add(gameover_btn);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        //洗牌按钮
        if (arg0.getSource() == clear_btn) {
            cm.gameStart(game, this);
            // 纸牌初始化
            cm.initCards();
            // 打乱纸牌顺序
            cm.randomCards();
            // 初始化一系列数值
            computer_dot = 0;
            game_dot = 0;
            if (computerCard[0] != null) {
                for (int n = 0; n < countComputer; n++) {
                    this.remove(computerCard[n]);
                }
                this.repaint();
            }
            if (gameCard[0] != null) {
                for (int n = 0; n < countGame; n++) {
                    this.remove(gameCard[n]);
                }
                this.repaint();
            }
            i = 0;
            countComputer = 0;
            countGame = 0;
            compute_btn.setEnabled(true);
        }

        //开始游戏按钮
        if (arg0.getSource() == compute_btn) {
            // 电脑抓牌
            for (; computer_dot < 15 && i < 52; i++) {
                if (cm.cards[i].value <= 10) {
                    computer_dot += cm.cards[i].value;
                } else {
                    computer_dot += 1;
                }
            }
            //牌堆移牌
            if (game[0] != null) {
                for (int j = 0; j <= i; j++) {
                    this.remove(game[j]);
                }
                this.repaint();
            }
            // 创建放置电脑纸牌的组件
            for (int k = 0; k < i; k++) {
                computerCard[k] = new JLabel();
                computerCard[k].setBorder(javax.swing.BorderFactory.createEtchedBorder());
                computerCard[k].setBounds(new Rectangle(100 + k * 30, 170, 101, 150));
                this.add(computerCard[k], 0);
            }
            //设置标签组件的图片为rear.jpg，即牌的背面
            for (int k = 0; k < i; k++) {
                computerCard[k].setIcon(new ImageIcon("images/rear.jpg"));
            }
            game_btn.setEnabled(true);
            clear_btn.setEnabled(false);
            compute_btn.setEnabled(false);
            //记录电脑抓牌数
            countComputer = i;
            System.out.println(computer_dot);
        }

        //玩家抓牌按钮
        if (arg0.getSource() == game_btn) {
            //玩家抓牌
            if (cm.cards[i].value <= 10) {
                game_dot += cm.cards[i].value;
            } else {
                game_dot += 1;
            }
            if (game_dot > 21) {
                game_btn.setEnabled(false);
            }
            //牌堆移牌
            if (game[i] != null) {
                this.remove(game[i]);
                this.repaint();
            }
            //玩家牌显示
            JLabel gam = new JLabel();
            gam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            gam.setBounds(new Rectangle(300 + i * 30, 170, 101, 150));
            this.add(gam, 0);
            //设置标签组件的图片为rear.jpg，即牌的背面
            gam.setIcon(new ImageIcon("images/" + cm.cards[i].getType() + "-" + cm.cards[i].getValue() + ".jpg"));
            gameCard[countGame++] = gam;
            i++;
            System.out.println(game_dot);
            gameover_btn.setEnabled(true);
        }

        //本轮游戏结束按钮
        if (arg0.getSource() == gameover_btn) {
            clear_btn.setEnabled(true);
            compute_btn.setEnabled(false);
            game_btn.setEnabled(false);
            gameover_btn.setEnabled(false);
            //电脑牌显示
            for (int k = 0; k < countComputer; k++) {
                computerCard[k].setIcon(new ImageIcon("images/" + cm.cards[k].getType() + "-" + cm.cards[k].getValue() + ".jpg"));
            }
            //公布结果
            if (computer_dot > 21 && game_dot > 21) {
                JOptionPane.showMessageDialog(this, "电脑点数：" + computer_dot + "\n" +
                        "你的点数：" + game_dot + " \n " +
                        "平局", "对局结果", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (computer_dot <= 21 && game_dot > 21) {
                JOptionPane.showMessageDialog(this, "电脑点数：" + computer_dot + "\n " +
                        "你的点数：" + game_dot + "\n  " +
                        "电脑赢", "对局结果", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (computer_dot > 21 && game_dot <= 21) {
                JOptionPane.showMessageDialog(this, "电脑点数：" + computer_dot + "\n" +
                        "你的点数：" + game_dot + "\n" +
                        "你赢了", "对局结果", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (computer_dot > game_dot) {
                JOptionPane.showMessageDialog(this, "电脑点数：" + computer_dot + "\n " +
                        "你的点数：" + game_dot + "\n " +
                        "电脑赢", "对局结果", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (computer_dot < game_dot) {
                JOptionPane.showMessageDialog(this, "电脑点数：" + computer_dot + "\n " +
                        "你的点数：" + game_dot + "\n " +
                        "你赢了", "对局结果", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "电脑点数：" + computer_dot + "\n " +
                        "你的点数：" + game_dot + "\n " +
                        "平局", "对局结果", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
    }
}
