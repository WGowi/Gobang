package BgMusic;

public class MusicFactory1 implements BackgroundMusicFactory
{
    @Override
    public BackgroundMusic produceBackMusic()
    {
        return new Music1();
    }
}
