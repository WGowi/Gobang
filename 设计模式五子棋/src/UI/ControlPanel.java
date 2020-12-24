package UI;

import BgMusic.*;
import Game.ChessboardPanel;
import Game.TwoPlayersGame;
import Observer.Obs;
import Observer.Subject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ControlPanel extends JPanel
{
    ImageIcon icon = new ImageIcon("img/头像.jpg");
    JLabel avatar = new JLabel("", JLabel.CENTER);

    JLabel name = new JLabel("姓名:小明", JLabel.CENTER);
    JLabel level = new JLabel("等级：新手", JLabel.CENTER);
    JLabel gameInfo = new JLabel("游戏模式:", JLabel.CENTER);
    JButton startGame = new JButton("开始新游戏");
    JComboBox gameType = new JComboBox();
    JButton regretChess = new JButton("悔棋");
    JLabel musicInfo = new JLabel("背景音乐:", JLabel.CENTER);
    JComboBox bgMusic = new JComboBox();
    JButton exit = new JButton("返回主页");
    BackgroundMusicFactory backgroundMusicFactory;
    BackgroundMusic backgroundMusic;
    MusicFactory1 musicFactory1 = new MusicFactory1();
    MusicFactory2 musicFactory2 = new MusicFactory2();
    MusicFactory3 musicFactory3 = new MusicFactory3();

    String[][] chess = new String[17][17];

    public ControlPanel(final ChessboardPanel chessboardPanel1)
    {
        avatar.setIcon(icon);

//        gameType.addItem("人人对战——黑方先手");
//        gameType.addItem("人机对战——人类先手");
//        gameType.addItem("人机对战——机器先手");

        bgMusic.addItem("凤求凰");
        bgMusic.addItem("渔舟唱晚");
        bgMusic.addItem("高山流水");

        name.setFont(new Font(name.getFont().getName(), name.getFont().getStyle(), 20));
        level.setFont(new Font(level.getFont().getName(), level.getFont().getStyle(), 20));
        gameInfo.setFont(new Font(gameInfo.getFont().getName(), gameInfo.getFont().getStyle(), 20));
        musicInfo.setFont(new Font(musicInfo.getFont().getName(), musicInfo.getFont().getStyle(), 20));


        backgroundMusicFactory = musicFactory1;
        backgroundMusic = musicFactory1.produceBackMusic();
        backgroundMusic.play();

        this.initChess(chessboardPanel1);

        this.newGame(chessboardPanel1);
        this.changeMusic();
        this.backPrevious(chessboardPanel1);
        this.isExit();

        this.add(avatar);
        this.add(name);
        this.add(level);
//        this.add(gameInfo);
//        this.add(gameType);
        this.add(startGame);
        this.add(regretChess);
        this.add(musicInfo);
        this.add(bgMusic);
        this.add(exit);
    }

    private void isExit()
    {
        exit.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    backgroundMusic.stop();
                    new BackHome().back();
                }
                catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    private void changeMusic()
    {
        bgMusic.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                String now_music = bgMusic.getSelectedItem().toString();
                backgroundMusic.stop();
                if (now_music.equals("凤求凰"))
                {
                    backgroundMusicFactory = musicFactory1;
                } else if (now_music.equals("渔舟唱晚"))
                {
                    backgroundMusicFactory = musicFactory2;
                } else if (now_music.equals("高山流水"))
                {
                    backgroundMusicFactory = musicFactory3;
                }
                backgroundMusic = backgroundMusicFactory.produceBackMusic();
                backgroundMusic.play();
            }
        });
    }

    private void newGame(final ChessboardPanel chessboardPanel1)
    {
        startGame.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                initChess(chessboardPanel1);
                chessboardPanel1.setCanPlay(true);
                chessboardPanel1.setChess(chess);
                chessboardPanel1.getChessInfo().clear();
                chessboardPanel1.saveMemento();
                chessboardPanel1.repaint();
                JOptionPane.showMessageDialog(ControlPanel.this, "游戏已开始");
            }
        });
    }

    public void initChess(ChessboardPanel chessboardPanel)
    {
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                chess[i][j] = "null";
            }
        }
        System.out.println(chessboardPanel.getFirst());
        if (chessboardPanel.getFirst().equals("machine"))
        {
            chess[8][8]="machine";
        }
    }

    private void backPrevious(ChessboardPanel chessboardPanel1)
    {
        regretChess.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("开始悔棋");
                chessboardPanel1.restoreMemento();
            }
        });
    }

    class BackHome extends Subject
    {
        @Override
        public void back() throws InterruptedException
        {
            for (Object obs:observers)
            {
                ((Obs)obs).response();
            }
        }
    }

}
