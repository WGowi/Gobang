package BgMusic;

public class MusicFactory2 implements BackgroundMusicFactory
{
    @Override
    public BackgroundMusic produceBackMusic()
    {
        return new Music2();
    }
}
