package Game;

import Memorandum.Memento;
import Piece.ChessFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SinglePlayerGame extends JPanel implements MouseListener, ChessboardPanel
{

    int[][] score = new int[17][17];
    String[][] chess = new String[17][17];
    String first = "human";
    BufferedImage bi = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
    java.awt.Graphics g2 = bi.createGraphics();
    Image img, blackChessImg, whiteChessImg, selectedBlackChessImg, selectedWhiteChessImg, humanChess, humanSelectChess, machineChess, machineSelectChess, selectImg;
    ImageIcon icon, selectIcon;
    int x, y;
    int goalX = -1;//目标位置x坐标
    int goalY = -1;//目标位置y坐标
    boolean canPlay = false;
    ArrayList chessInfo = new ArrayList();

    public SinglePlayerGame(String first) throws IOException, InterruptedException
    {
        this.first = first;
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                chess[i][j] = "null";
//                select[i][j] = false;
            }
        }

        if (first.equals("machine"))
        {
            goalX = 8;
            goalY = 8;
            chess[goalX][goalY] = "machine";
        }

        this.saveMemento();

        icon = new ImageIcon("img/棋盘.jpg");
        img = icon.getImage();

        ChessFactory chessFactory = new ChessFactory();
        blackChessImg = chessFactory.getChess("黑棋").getChess();
        whiteChessImg = chessFactory.getChess("白棋").getChess();
        selectedBlackChessImg = chessFactory.getChess("选中黑棋").getChess();
        selectedWhiteChessImg = chessFactory.getChess("选中白棋").getChess();
//        selectImg = selectIcon.getImage();

        if (first.equals("human"))
        {
            humanChess = blackChessImg;
            humanSelectChess = selectedBlackChessImg;
            machineChess = whiteChessImg;
            machineSelectChess = selectedWhiteChessImg;
        } else
        {
            humanChess = whiteChessImg;
            humanSelectChess = selectedWhiteChessImg;
            machineChess = blackChessImg;
            machineSelectChess = selectedBlackChessImg;
        }

        this.addMouseListener(this);


    }



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
        // 画棋子
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                if (chess[i][j].equals("human"))
                {
                    int tempX = 160 + 40 * i;
                    int tempY = 30 + 40 * j;
                    if (i == x && j == y)
                    {
                        g2.drawImage(humanSelectChess, tempX - 15, tempY - 15, this);
                    } else
                    {
                        g2.drawImage(humanChess, tempX - 15, tempY - 15, this);
                    }
                } else if (chess[i][j].equals("machine"))
                {
                    int tempX = 160 + 40 * i;
                    int tempY = 30 + 40 * j;
                    if (i == goalX && j == goalY)
                    {
                        g2.drawImage(machineSelectChess, tempX - 15, tempY - 15, this);
                    } else
                    {
                        g2.drawImage(machineChess, tempX - 15, tempY - 15, this);
                    }
                }
            }
        }
        g.drawImage(bi, 0, 0, this);

    }

    private boolean checkWin(int x, int y)
    {
        boolean flag = false;
        String owner = chess[x][y];
        // 横向判断
        int i = 1, count = 1;
        while ((x + i < 17) && (owner.equals(chess[x + i][y])))
        {
            i++;
            count++;
        }
        i = 1;
        while ((x - i >= 0) && (owner.equals(chess[x - i][y])))
        {
            i++;
            count++;
        }
        // 纵向判断
        int i2 = 1, count2 = 1;
        while ((y + i2 < 17) && (owner.equals(chess[x][y + i2])))
        {
            i2++;
            count2++;
        }
        i2 = 1;
        while ((y - i2 >= 0) && (owner.equals(chess[x][y - i2])))
        {
            i2++;
            count2++;
        }
        // 右上-左下判断
        int i3 = 1, count3 = 1;
        while ((x + i3 < 17) && (y - i3 >= 0) && (owner.equals(chess[x + i3][y - i3])))
        {
            i3++;
            count3++;
        }
        i3 = 1;
        while ((x - i3 >= 0) && (y + i3 < 17) && (owner.equals(chess[x - i3][y + i3])))
        {
            i3++;
            count3++;
        }
        // 左上-右下判断
        int i4 = 1, count4 = 1;
        while ((x + i4 < 17) && (y + i4 < 17) && (owner.equals(chess[x + i4][y + i4])))
        {
            i4++;
            count4++;
        }
        i4 = 1;
        while ((x - i4 >= 0) && (y - i4 >= 0) && (owner.equals(chess[x - i4][y - i4])))
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
                    chess[x][y] = "human";
                    this.repaint();
                    if (checkWin(x, y))
                    {
                        canPlay = false;
                        JOptionPane.showMessageDialog(this, "人类获胜，游戏结束！");
                    }
                    searchLocation();
                    this.repaint();
                    if (checkWin(goalX, goalY))
                    {
                        canPlay = false;
                        JOptionPane.showMessageDialog(this, "电脑获胜，游戏结束！");
                    }
                    this.saveMemento();
                } else
                {
                    JOptionPane.showMessageDialog(this, "此处已有棋子，请重新落子");
                }
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "请点击开始新游戏");
        }
    }

    //各种五元组情况评分表
    public int tupleScore(int humanChessmanNum, int machineChessmanNum)
    {
        //1.既有人类落子，又有机器落子，判分为0
        if (humanChessmanNum > 0 && machineChessmanNum > 0)
        {
            return 0;
        }
        //2.全部为空，没有落子，判分为7
        if (humanChessmanNum == 0 && machineChessmanNum == 0)
        {
            return 7;
        }
        //3.机器落1子，判分为35
        if (machineChessmanNum == 1)
        {
            return 35;
        }
        //4.机器落2子，判分为800
        if (machineChessmanNum == 2)
        {
            return 800;
        }
        //5.机器落3子，判分为15000
        if (machineChessmanNum == 3)
        {
            return 15000;
        }
        //6.机器落4子，判分为800000
        if (machineChessmanNum == 4)
        {
            return 800000;
        }
        //7.人类落1子，判分为15
        if (humanChessmanNum == 1)
        {
            return 15;
        }
        //8.人类落2子，判分为400
        if (humanChessmanNum == 2)
        {
            return 400;
        }
        //9.人类c落3子，判分为1800
        if (humanChessmanNum == 3)
        {
            return 1800;
        }
        //10.人类落4子，判分为100000
        if (humanChessmanNum == 4)
        {
            return 100000;
        }
        return -1;//若是其他结果肯定出错了。这行代码根本不可能执行
    }

    public void searchLocation()
    {
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                score[i][j] = 0;
            }
        }

        int humanChessmanNum = 0;
        int machineChessmanNum = 0;
        int tupleScoreTmp = 0;//五元组得分临时变量
        int maxScore = -1;//最大分数

        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                int k = j;
                while (k < j + 5)
                {

                    if (chess[i][k].equals("machine"))
                    {
                        machineChessmanNum++;
                    } else if (chess[i][k].equals("human"))
                    {
                        humanChessmanNum++;
                    }
                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++)
                {
                    score[i][k] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //2.扫描纵向15行
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                int k = j;
                while (k < j + 5)
                {
                    if (chess[k][i].equals("machine"))
                    {
                        machineChessmanNum++;
                    } else if (chess[k][i].equals("human"))
                    {
                        humanChessmanNum++;
                    }

                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++)
                {
                    score[k][i] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }
        //3.扫描右上角到左下角上侧部分
        for (int i = 16; i >= 4; i--)
        {
            for (int k = i, j = 0; j < 17 && k >= 0; j++, k--)
            {
                int m = k;
                int n = j;
                while (m > k - 5 && k - 5 >= -1)
                {
                    if (chess[m][n].equals("machine"))
                    {
                        machineChessmanNum++;
                    } else if (chess[m][n].equals("human"))
                    {
                        humanChessmanNum++;
                    }
                    m--;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k - 5)
                {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m > k - 5; m--, n++)
                    {
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //4.扫描右上角到左下角下侧部分
        for (int i = 1; i < 17; i++)
        {
            for (int k = i, j = 16; j >= 0 && k < 17; j--, k++)
            {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15)
                {
                    if (chess[n][m].equals("machine"))
                    {
                        machineChessmanNum++;
                    } else if (chess[n][m].equals("human"))
                    {
                        humanChessmanNum++;
                    }

                    m++;
                    n--;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5)
                {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n--)
                    {
                        score[n][m] += tupleScoreTmp;
                    }
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //5.扫描左上角到右下角上侧部分
        for (int i = 0; i < 13; i++)
        {
            for (int k = i, j = 0; j < 17 && k < 17; j++, k++)
            {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 17)
                {
                    if (chess[m][n].equals("machine"))
                    {
                        machineChessmanNum++;
                    } else if (chess[m][n].equals("human"))
                    {
                        humanChessmanNum++;
                    }

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5)
                {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++)
                    {
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //6.扫描左上角到右下角下侧部分
        for (int i = 1; i < 13; i++)
        {
            for (int k = i, j = 0; j < 17 && k < 17; j++, k++)
            {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 17)
                {
                    if (chess[n][m].equals("machine"))
                    {
                        machineChessmanNum++;
                    } else if (chess[n][m].equals("human"))
                    {
                        humanChessmanNum++;
                    }

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5)
                {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++)
                    {
                        score[n][m] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                if (chess[i][j].equals("null") && score[i][j] > maxScore)
                {
                    goalX = i;
                    goalY = j;
                    maxScore = score[i][j];
                }
            }
        }
        if (goalX != -1 && goalY != -1)
        {
            chess[goalX][goalY] = "machine";
        }
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


    @Override
    public void setCanPlay(boolean canPlay)
    {
        this.canPlay = canPlay;
    }

    @Override
    public void setChess(String[][] chess)
    {
        this.chess = chess;
    }

    @Override
    public void restoreMemento()
    {
        if (haveChess())
        {
            if (chessInfo.size() > 1)
            {
                Memento memento1;
                memento1 = (Memento) chessInfo.get(chessInfo.size() - 2);
                chessCopy(this.chess, memento1.getChess());
                chessInfo.remove(chessInfo.size() - 1);
                if (!canPlay)
                {
                    canPlay = true;
                }
                this.repaint();
//            showArray();
            } else
            {
                JOptionPane.showMessageDialog(this, "棋盘上没有棋子无法悔棋");
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "游戏尚未开始，无法悔棋");
        }
    }

    @Override
    public Memento saveMemento()
    {
        Memento memento1 = new Memento(chess); // 深复制
        this.chessInfo.add(new Memento(chess));
        System.out.println("保存成功");
//        showArray();
        return memento1;
    }

    public void chessCopy(String[][] str1, String[][] str2)
    {
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                str1[i][j] = str2[i][j];
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

    @Override
    public String getFirst()
    {
        return first;
    }
}

