package cn.encdata.sfmi.game21;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AboutFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jLabel1 = new JLabel("游戏规则");
	JLabel jLabel2 = new JLabel("声明");
	JTextArea textArea1 = new JTextArea();
	JTextArea textArea2 = new JTextArea();
	public AboutFrame() {
		getContentPane().setLayout(null);
		jLabel1.setBounds(new Rectangle(27, 0, 97, 36));
		jLabel2.setBounds(new Rectangle(26, 176, 80, 27));
		textArea1.setColumns(40);
		textArea1.setEditable(false);
		textArea1.setLineWrap(true);
		textArea1.setText("电脑先抓牌，玩家后抓牌。计算自己的面值总数，比较面值数，如果面值总数都大于或等于21，则平局；如果玩家和电脑的面值总数有一个大于21点，另一个不大于21点，则不大于21点的为赢家；如果玩家和电脑的面值总数都不大于21，则面值总数大的为赢家。");
		textArea1.setBounds(new Rectangle(25, 36, 392, 130));
		textArea1.setFont(new Font("楷体", Font.PLAIN, 16));
		textArea2.setEditable(false);
		textArea2.setLineWrap(true);
		textArea2.setBounds(new Rectangle(26, 207, 398, 63));
		textArea2.setText("该游戏中，纸牌的图片来自于WindowsXP的纸牌游戏，图片权属于原作者所有！");
		textArea2.setFont(new Font("楷体", Font.PLAIN, 16));
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(jLabel2);
		this.getContentPane().add(textArea1);
		this.getContentPane().add(textArea2);
		this.setSize(450, 300);
		this.setTitle("关于");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //获得屏幕的宽和高
		Dimension frameSize = this.getSize();  //获得当前窗体的宽和高
		//设置窗体居中
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		this.setVisible(true);
	}
}
