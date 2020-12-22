package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class StartUI extends JFrame
{

    JButton game1=new JButton("开始单人游戏——人类先手");
    JButton game2=new JButton("开始单人游戏——电脑先手");
    JButton game3=new JButton("开始双人游戏");
    JButton exit=new JButton("退出游戏");
    JLabel label=new JLabel("欢迎来到五子棋游戏");

    public StartUI() throws HeadlessException
    {
        initWindow();
        getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel panel=new ImagePanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        label.setBounds(175,90,300,45);
        game1.setBounds(50,250,200,25);
        game2.setBounds(300,250,200,25);
        game3.setBounds(50,300,200,25);
        exit.setBounds(300,300,200,25);

        this.joinGame1();
        this.joinGame2();
        this.joinGame3();
        this.isExit();

        panel.add(label);
        panel.add(game1);
        panel.add(game2);
        panel.add(game3);
        panel.add(exit);
        this.setVisible(true);
    }

    private void initWindow()
    {
        this.setTitle("五子棋游戏");
        int window_width = 537;
        int window_height = 357;
        this.setSize(window_width, window_height);
        int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screen_width - window_width) / 2, (screen_height - window_height) / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void isExit()
    {
        exit.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int result = JOptionPane.showConfirmDialog(StartUI.this, "确认退出?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }

    private void joinGame1()
    {
        game1.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StartUI.this.dispose();
                try
                {
                    new Game1();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    private void joinGame2()
    {
        game2.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StartUI.this.dispose();
                try
                {
                    new Game2();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    private void joinGame3()
    {
        game3.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StartUI.this.dispose();
                try
                {
                    new Game3();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                catch (InterruptedException interruptedException)
                {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    class ImagePanel extends JPanel
    {
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            ImageIcon icon = new ImageIcon("img/背景图片.jpeg");
            g.drawImage(icon.getImage(), 0, 0, null);

        }
    }

}