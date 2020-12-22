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
