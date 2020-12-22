package Piece;

import javax.swing.*;
import java.awt.*;

public class SelectedWhiteChess implements ChessPool
{
    private String type;

    public SelectedWhiteChess(String type)
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
        ImageIcon selectedWhiteChessIcon = new ImageIcon("img/选中白棋.png");
        return selectedWhiteChessIcon.getImage();
    }
}
