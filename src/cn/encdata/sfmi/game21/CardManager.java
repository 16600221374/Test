package cn.encdata.sfmi.game21;

import java.awt.Container;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardManager {
    public Card[] cards = new Card[52];
    public int m = 0;

    //初始化一副52张纸牌
    public void initCards() {
        for (int type = 1; type <= 4; type++) {
            for (int value = 1; value <= 13; value++) {
                Card card = new Card();
                card.setType(type);
                card.setValue(value);
                cards[m++] = card;
            }
        }
        m = 0;
			/*for(Card card1:cards){
				System.out.println(card1.type +'-'+ card1.value);
			}*/
    }

    //随机打乱这52张纸牌
    public void randomCards() {
        Random random = new Random();
        for (int index = cards.length - 1; index >= 0; index--) {
            exchange(random.nextInt(index + 1), index);
        }

    }

    //更换位置
    private void exchange(int randomIndex, int beforeIndex) {
        Card temp = cards[randomIndex];
        cards[randomIndex] = cards[beforeIndex];
        cards[beforeIndex] = temp;
    }


    //显示纸牌图片
    public void gameStart(JLabel game[], Container c) {
        //在容器中删除标签组件
        if (game[0] != null) {
            for (int i = 0; i < 52; i++) {
                c.remove(game[i]);
            }
            c.repaint();
        }
        //在容器中放置52个标签组件用于放置图片
        for (int i = 0; i < 52; i++) {
            game[i] = new JLabel();
            game[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
            game[i].setBounds(new Rectangle(100 + i * 10, 10, 101, 150));
            c.add(game[i]);
        }
        //设置标签组件的图片为rear.jpg，即牌的背面
        for (int i = 0; i < 52; i++) {
            game[i].setIcon(new ImageIcon("images/rear.jpg"));
        }
    }
}
