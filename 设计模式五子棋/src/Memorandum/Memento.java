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
