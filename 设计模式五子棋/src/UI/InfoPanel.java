package UI;

import Observer.Obs;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements Obs
{
    String bTime = "1:00";
    String wTime = "1:00";
    String chessMessage = "黑方先行";
    int i = 1;

    public InfoPanel() throws InterruptedException
    {
        JLabel blackTime = new JLabel("黑方用时:" + bTime, JLabel.LEFT);
        JLabel chessInfo = new JLabel("游戏信息:" + chessMessage, JLabel.CENTER);
        JLabel whiteChess = new JLabel("白方用时:" + wTime, JLabel.RIGHT);
//        this.setSize(1000,30);
        this.add(blackTime);
        this.add(chessInfo);
        this.add(whiteChess);
//        this.paint();
    }

//    public void paint(Graphics g)
//    {
//        System.out.println("1、paint");
//        g.drawString("黑方用时:"+bTime,250,10);
//        g.drawString(chessMessage,500,10);
//        g.drawString("白方用时:"+wTime,750,10);
//        System.out.println(chessMessage);
//        System.out.println("2、paint");
//    }

    public void updateChessMessage() throws InterruptedException
    {
        if (i % 2 == 0)
        {
            chessMessage = "轮到黑方";
        } else
        {
            chessMessage = "轮到白方";
        }
        i++;
//        chessInfo.setText(chessMessage);
//        chessInfo.updateUI();
//        Thread.sleep(1000);
//        System.out.println(chessInfo.getText());
//        System.out.println("开始重绘");
        this.removeAll();

        this.repaint();
//        System.out.println("重绘结束");
    }

    @Override
    public void response() throws InterruptedException
    {
//        updateChessMessage();
//        System.out.println("开始重绘");
        if (i % 2 == 0)
        {
            chessMessage = "轮到黑方";
        } else
        {
            chessMessage = "轮到白方";
        }
        i++;
        this.removeAll();
        System.out.println("======");
//        JLabel` blackTime = new JLabel("黑方用时:" + bTime, JLabel.LEFT);
//        JLabel chessInfo = new JLabel("游戏信息:" + chessMessage, JLabel.CENTER);
//        JLabel whiteChess = new JLabel("白方用时:" + wTime, JLabel.RIGHT);
//        this.add(blackTime);
//        this.add(chessInfo);
//        this.add(whiteChess);
//        System.out.println("重绘结束");
//        new Thread(() ->
//        {
//            try
//            {
//                updateChessMessage();
//                System.out.println("开始重绘");
//                InfoPanel.this.repaint();
//                System.out.println("重绘结束");
//            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }).start();
    }

}
