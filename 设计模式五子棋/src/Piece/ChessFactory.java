package Piece;

import java.util.ArrayList;

public class ChessFactory
{
    private ArrayList chess = new ArrayList();

    public ChessFactory()
    {
        ChessPool blackChess = new BlackChess("黑棋");
        ChessPool whiteChess = new WhiteChess("白棋");
        ChessPool selectedBlackChess = new SelectedBlackChess("选中黑棋");
        ChessPool selectedWhiteChess = new SelectedWhiteChess("选中白棋");
        chess.add(blackChess);
        chess.add(whiteChess);
        chess.add(selectedBlackChess);
        chess.add(selectedWhiteChess);
    }
    public ChessPool getChess(String type)
    {
        if (type.equals("黑棋"))
        {
            return (ChessPool) chess.get(0);
        }
        else if (type.equals("白棋"))
        {
            return (ChessPool) chess.get(1);
        }
        else if (type.equals("选中黑棋"))
        {
            return (ChessPool) chess.get(2);
        }
        else if (type.equals("选中白棋"))
        {
            return (ChessPool) chess.get(3);
        }
        else
        {
            return null;
        }
    }
}
