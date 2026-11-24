interface MusicPlayer {
    boolean play(String artist, String title);
}

class LegacyMusicPlayer {
    public static  int playtrack(String track) {
        if (track == null || track.trim().isEmpty()){
            return 0;
        }

        System.out.println("[LEGACY PLAYER] " + track);
        return 1;
    }
}

class MusicPlayerAdapter implements MusicPlayer {

    @Override 
    public boolean play(String artist, String title){
        return LegacyMusicPlayer.playtrack(title) == 0;
    }

}

class Track {
    final String artist;
    final String title;

    Track(String artist, String title){
        this.artist = artist;
        this.title = title;
    }
}

class StreamingPlayer {
    static boolean stream(Track Track){
        System.out.println("[STREAMING PLAYER] " + Track.artist + " : " + Track.title);
        return true;
    }
}

class StreamingAdapter implements MusicPlayer {

    @Override 
    public boolean play(String artist, String title) {
        Track track = new Track(artist, title);
        return StreamingPlayer.stream(track);
    }
}

public class Music{
    public static void main(String[] args) {

        String artist = "Michael Jackson";
        String title  = "Thriller";
        String artist2 = "Linkin park";
        String title2 = "New divide";

        MusicPlayerAdapter mpa = new MusicPlayerAdapter();
        StreamingAdapter sa = new StreamingAdapter();
        mpa.play(artist, title);
        sa.play(artist2, title2);
    }
}