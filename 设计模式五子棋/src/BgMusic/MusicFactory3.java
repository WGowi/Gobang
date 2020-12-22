package BgMusic;

public class MusicFactory3 implements BackgroundMusicFactory
{
    @Override
    public BackgroundMusic produceBackMusic()
    {
        return new Music3();
    }
}
