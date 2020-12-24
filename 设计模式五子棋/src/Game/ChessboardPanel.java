package Game;

import Memorandum.Memento;

import java.awt.*;
import java.util.ArrayList;

public interface ChessboardPanel
{
    public void setCanPlay(boolean canPlay);
    public void setChess(String[][] chess);
    public void restoreMemento();
    public Memento saveMemento();
    public void paint(Graphics g);
    public void repaint();
    public String getFirst();
    public ArrayList getChessInfo();
    public void setChessInfo(ArrayList chessInfo);
}
