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
