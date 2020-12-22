package BgMusic;

import Observer.Obs;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music3 extends JFrame implements BackgroundMusic, Obs
{
    File f;
    URI uri;
    URL url;
    AudioClip aau;


    @Override
    public void play()
    {
        try
        {
            f = new File("music/高山流水.wav");
            uri = f.toURI();
            url = uri.toURL();  //解析地址
            aau = Applet.newAudioClip(url);
            aau.loop();  //循环播放
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void stop()
    {
        aau.stop();
    }

    @Override
    public void response() throws InterruptedException
    {
        System.out.println("+++++++");
        this.stop();
    }
}
