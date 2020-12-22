package Piece;

import javax.swing.*;
import java.awt.*;

public class SelectedBlackChess implements ChessPool
{
    private  String type;

    public SelectedBlackChess(String type)
    {
        this.type = type;
    }

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public Image getChess()
    {
        ImageIcon selectedBlackChesIcon = new ImageIcon("img/选中黑棋.png");
        return selectedBlackChesIcon.getImage();
    }
}
