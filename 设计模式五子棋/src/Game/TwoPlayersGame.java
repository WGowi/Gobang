package Game;

import Memorandum.Memento;
import Observer.Obs;
import Observer.Subject;
import Piece.ChessFactory;
import UI.InfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TwoPlayersGame extends JPanel implements MouseListener,ChessboardPanel
{
    // 背景图片
    ImageIcon icon, selectIcon;
    Image img, blackChessImg, whiteChessImg, selectedBlackChessImg, selectedWhiteChessImg, selectImg;
    //消息
    String message = "黑方先行";
    //棋子位置
    int x = 0;
    int y = 0;
    int x_ = 0;
    int y_ = 0;
    // 之前下的所有棋子坐标
    private String[][] chess = new String[17][17];
    //    private boolean[][] select = new boolean[17][17];
    // 是否为黑
    boolean isBlack = true;
    // 是否继续
    boolean canPlay = false;

    BufferedImage bi = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
    Graphics g2 = bi.createGraphics();


    JPanel infoPanel;

    ArrayList chessInfo = new ArrayList();

    public TwoPlayersGame() throws IOException, InterruptedException
    {
        this.infoPanel = infoPanel;
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                chess[i][j] = "null";
//                select[i][j] = false;
            }
        }

        icon = new ImageIcon("img/棋盘.jpg");
        img = icon.getImage();

        ChessFactory chessFactory = new ChessFactory();
        blackChessImg = chessFactory.getChess("黑棋").getChess();
        whiteChessImg = chessFactory.getChess("白棋").getChess();
        selectedBlackChessImg = chessFactory.getChess("选中黑棋").getChess();
        selectedWhiteChessImg = chessFactory.getChess("选中白棋").getChess();
//        selectImg = selectIcon.getImage();

        this.addMouseListener(this);
        this.saveMemento();

    }

//    public void setSelect(int i, int j, boolean flag)
//    {
//        select[i][j] = flag;
//    }

    public void paint(Graphics g)
    {
        //双缓冲技术
        g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        g2.setColor(Color.BLACK);
        float width = 2.0f;//你需要的宽度
        Graphics2D g3 = (Graphics2D) g2;//之前有一个Graphics的对象为g
        g3.setStroke(new BasicStroke(width));
        for (int i = 0; i < 17; i++)
        {
            g2.drawLine(160, 30 + 40 * i, 800, 30 + 40 * i);
            g2.drawLine(160 + 40 * i, 30, 160 + 40 * i, 670);
        }
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                if (chess[i][j].equals("black"))
                {
                    int tempX = 160 + 40 * i;
                    int tempY = 30 + 40 * j;
                    if (i == x && j == y)
                    {
                        g2.drawImage(selectedBlackChessImg, tempX - 15, tempY - 15, this);
                    } else
                    {
                        g2.drawImage(blackChessImg, tempX - 15, tempY - 15, this);
                    }
                }
                if (chess[i][j].equals("white"))
                {
                    int tempX = 160 + 40 * i;
                    int tempY = 30 + 40 * j;
                    if (i == x && j == y)
                    {
                        g2.drawImage(selectedWhiteChessImg, tempX - 15, tempY - 15, this);
                    } else
                    {
                        g2.drawImage(whiteChessImg, tempX - 15, tempY - 15, this);
                    }
                }
            }
        }
        g.drawImage(bi, 0, 0, this);
    }

    @Override
    public String getFirst()
    {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (canPlay)
        {
            x = e.getX();
            y = e.getY();
            if (x >= 155 && x <= 805 && y >= 25 && y <= 675)
            {
                x = (int) Math.rint((x - 160) / 40.0);
                y = (int) Math.rint((y - 30) / 40.0);
                if (chess[x][y].equals("null"))
                {
                    if (isBlack)
                    {
                        chess[x][y] = "black";
                        isBlack = false;
                        message = "轮到白方";
                    } else
                    {
                        chess[x][y] = "white";
                        isBlack = true;
                        message = "轮到黑方";
                    }
                    this.saveMemento();
                } else
                {
                    JOptionPane.showMessageDialog(this, "此处已有棋子，请重新落子");
                }
                this.repaint();
                if (this.checkWin())
                {
                    if (chess[x][y].equals("black"))
                    {
                        JOptionPane.showMessageDialog(this, "黑方获胜，游戏结束!");
                        canPlay = false;
                    }
                    if (chess[x][y].equals("white"))
                    {
                        JOptionPane.showMessageDialog(this, "白方获胜，游戏结束!");
                        canPlay = false;
                    }
                }
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "请点击开始新游戏");
        }
    }

    public String getMessage()
    {
        return message;
    }

    private boolean checkWin()
    {
        boolean flag = false;
        String color = chess[x][y];
        // 横向判断
        int i = 1, count = 1;
        while ((x + i < 17) && (color.equals(chess[x + i][y])))
        {
            i++;
            count++;
        }
        i = 1;
        while ((x - i >= 0) && (color.equals(chess[x - i][y])))
        {
            i++;
            count++;
        }
        // 纵向判断
        int i2 = 1, count2 = 1;
        while ((y + i2 < 17) && (color.equals(chess[x][y + i2])))
        {
            i2++;
            count2++;
        }
        i2 = 1;
        while ((y - i2 >= 0) && (color.equals(chess[x][y - i2])))
        {
            i2++;
            count2++;
        }
        // 右上-左下判断
        int i3 = 1, count3 = 1;
        while ((x + i3 < 17) && (y - i3 >= 0) && (color.equals(chess[x + i3][y - i3])))
        {
            i3++;
            count3++;
        }
        i3 = 1;
        while ((x - i3 >= 0) && (y + i3 < 17) && (color.equals(chess[x - i3][y + i3])))
        {
            i3++;
            count3++;
        }
        // 左上-右下判断
        int i4 = 1, count4 = 1;
        while ((x + i4 < 17) && (y + i4 < 17) && (color.equals(chess[x + i4][y + i4])))
        {
            i4++;
            count4++;
        }
        i4 = 1;
        while ((x - i4 >= 0) && (y - i4 >= 0) && (color.equals(chess[x - i4][y - i4])))
        {
            i4++;
            count4++;
        }
        if (count >= 5 || count2 >= 5 || count3 >= 5 || count4 >= 5)
        {
            flag = true;
        }
        return flag;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }


    public void setChess(String[][] chess)
    {
        this.chess = chess;
    }

    public void setCanPlay(boolean canPlay)
    {
        this.canPlay = canPlay;
    }

    public String[][] getChess()
    {
        return chess;
    }

    public Memento saveMemento()
    {
        Memento memento1 = new Memento(chess); // 深复制
        this.chessInfo.add(new Memento(chess));
        System.out.println("保存成功");
//        showArray();
        return memento1;
    }

    public void restoreMemento()
    {
        if (haveChess())
        {
            if (chessInfo.size() > 1)
            {
                Memento memento1;
                memento1 = (Memento) chessInfo.get(chessInfo.size() - 2);
                chessCopy(this.chess,memento1.getChess());
                chessInfo.remove(chessInfo.size() - 1);
                this.isBlack = !this.isBlack;
                if (!canPlay)
                {
                    canPlay=true;
                }
                this.repaint();
//            showArray();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"棋盘上没有棋子无法悔棋");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"游戏尚未开始，无法悔棋");
        }
    }

    public void chessCopy(String [][] str1,String [][] str2)
    {
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                str1[i][j]=str2[i][j];
            }
        }
    }

    public boolean haveChess()
    {
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                if (!chess[i][j].equals("null"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void showArray()
    {
        for (int i = 0; i < chessInfo.size(); i++)
        {
            Memento memento= (Memento) chessInfo.get(i);
            String [][] s=memento.getChess();
            System.out.println("第"+(i+1)+"次:");
            for (int j = 0; j < 17; j++)
            {
                for (int k = 0; k < 17; k++)
                {
                    System.out.print(s[j][k]+" ");
                }
                System.out.println("");
            }
        }
    }

}

