# 采用5种设计模式的五子棋

@[toc]

## 一、设计要求

仿照教材上面围棋软件的设计思路，设计一款五子棋，采用至少5种设计模式

 

## 二、引言

五子棋最早源于中国古代尧帝时期，最初流行于少数民族地区，围棋就是从

那时的五子棋渐渐的演变而来的。后来在炎黄子孙的后代流行开来。五子棋棋具虽然与围棋相同，但下法却完全不同。五子棋两人对局，轮流下棋，先将五子连成一行者为胜。后来，五子棋由朝鲜使臣带到朝鲜，再有日本人带到日本。而真正使五子棋发扬光大的是日本。五子棋刚到日本，只有在王室和贵族中间玩，后来被出入皇宫的下人偷偷的传入民间。

 

## 三、设计模式

### 4.1 采用的设计模式

本项目一共使用了5种设计模式，分别是工厂方法模式、享元模式、备忘录模式、观察者模式、策略模式。其中工厂方法模式给用户选择背景音乐、享元模式用于设计五子棋的棋子类、备忘录模式来实现悔棋功能，观察者模式来实现不同类中的JPanel与JFrame通信来实现关闭窗口，策略模式来提供用户来选择人机模式与人人模式。

 

### 4.2  设计模式结构视图

#### 4.2.1 工厂方法模式

![](https://img-blog.csdnimg.cn/img_convert/fa018a3c6a9de2e89bdf74c808aa9489.png)

图 1工厂方法模式视图

 

 

 

 

 

#### 4.2.2 享元模式

![](https://img-blog.csdnimg.cn/img_convert/dc6d178d4f180b8921183797dbfce724.png)
图 2享元模式视图

 

 

 

 

 

 

#### 4.2.3 备忘录模式

![](https://img-blog.csdnimg.cn/img_convert/2a86d10f8d210da6c32ee57ece62ff49.png)
图 3备忘录模式视图

 

 

 

 

#### 4.2.4 观察者模式

![](https://img-blog.csdnimg.cn/img_convert/431a7c47e4fe99492784988cb05eff78.png)

图 4观察者模式视图

 

 

 

 

 

 

 

#### 4.2.5 策略模式

![](https://img-blog.csdnimg.cn/img_convert/c189f4b8b24f58b2ea63fa5aa8284aed.png)

图 5策略模式视图

 

 

 

## 四、  系统设计

### 4.1 背景音乐选择——简单工厂方法模式

![](https://img-blog.csdnimg.cn/img_convert/eeaeca7036f04cc51f80bf4a8b104b07.png)

图 6工厂方法模式实例类类图

 

 

 

 

 

 

### 4.2 棋子对象——享元模式

![](https://img-blog.csdnimg.cn/img_convert/27033e8099a548a3642a2469a51b5f01.png)

图 7享元模式实例类图

 

### 4.3 悔棋——备忘录模式

![](https://img-blog.csdnimg.cn/img_convert/30358c66a008f18ca93c146f5e3fc46d.png)

图 8备忘录模式实例类图

 

 

 

 

 

 

 

 

 

### 4.4 JPanel与JFrmae的通信——观察者模式

![](https://img-blog.csdnimg.cn/img_convert/4e30722e975a8f76aba7d4c8d0cbca75.png)
图 9观察者模式实例类图

 

 

 

 

 

 

 

 

 

 

 

 

 

### 4.5 人机对战人人对战选择——策略模式

![](https://img-blog.csdnimg.cn/img_convert/5eb9e1b39222db41282caf47bbafaf6b.png)

图 10策略模式实例类图

 

 

 

 

 

 

 

 

 

### 4.6 分析类

![](https://img-blog.csdnimg.cn/img_convert/fb1f71243b42e29005aa13b3c511e15b.png)
图 11分析类类图

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

## 五、  系统实现
### 5.1 部分代码辅助说明

```java
package Observer;

import java.util.ArrayList;

public abstract class Subject
{
    protected static ArrayList observers = new ArrayList();

    public static void attach(Obs obs)
    {
        observers.add(obs);
    }

    public void detach(Obs obs)
    {
        observers.remove(obs);
    }

    public abstract void back() throws InterruptedException;
}

```


```java
package Observer;

public interface Obs
{
    void response() throws InterruptedException;
}

```

```java
package Memorandum;

public class Memento
{
    protected String [][] chess=new String[17][17];

    public Memento(String[][] chess)
    {
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                this.chess[i][j]=chess[i][j];
            }
        }
    }

    public String[][] getChess()
    {
        return chess;
    }

    public void setChess(String[][] chess)
    {
        this.chess = chess;
    }
}

```

```java
package Game;

import Memorandum.Memento;

import java.awt.*;

public interface ChessboardPanel
{
    public void setCanPlay(boolean canPlay);
    public void setChess(String[][] chess);
    public void restoreMemento();
    public Memento saveMemento();
    public void paint(Graphics g);
    public void repaint();
    public String getFirst();
}

```

```java
package BgMusic;

public interface BackgroundMusicFactory
{
    public BackgroundMusic produceBackMusic();

}

```

```java
package BgMusic;

public interface BackgroundMusic
{
    public void play();
    public void stop();
}

```

### 5.2  系统界面

![](https://img-blog.csdnimg.cn/img_convert/74a7d31cade092c57ec28bbef3b4ae08.png)

图 12游戏进入界面

![](https://img-blog.csdnimg.cn/img_convert/f3c3f5de3c76241986e8305b56659201.png)

图 13游戏开始界面

 

![](https://img-blog.csdnimg.cn/img_convert/e4a9102b1d6b069d65d96f56fa07d2e7.png)

图 14游戏开始提示界面

 

![](https://img-blog.csdnimg.cn/img_convert/33eb6b56eb2ef6fab3f2b991177e63d9.png)

图 15人机对战且电脑先手界面

 

![](https://img-blog.csdnimg.cn/img_convert/8a2c1c1628686b426f062a42a2bec0df.png)

图 16悔棋提示界面

 

 

![](https://img-blog.csdnimg.cn/img_convert/7d7eb320ef359cd748b2dd88e4072767.png)

图 17背景音乐切换界面

 

 

## 六、  结语

此次实验比较有挑战性，要运用5中设计模式来实现五子棋，五子棋的算法其实十分常见，但是里面的人机算法就比较有挑战性，我首先想使用贪心算法来实现电脑的落子，但是由于在最优子结构那里不知道怎么很好的判断，去网上查了一下，可以通过赋予每种落子的可能来实现赋权。通过比较权值的大小进行判断，而且在设计模式中我首先是写了一个人人对战的算法，那个时候应该感觉还缺几个设计模式，所以有打算写一个人机对战的算法来添加一个策略模式，在这里的我感觉来改动过程中我感觉到了，设计模式真的能很好的解决耦合性的问题，对于传统的设计来比较，使用设计模式之后，明显降低了耦合度，而且还在一定程度上满足开闭原则，对整体代码来说，虽然代码流程会比之前复杂，但是整个代码的质量明显提升了一个档次。

 
