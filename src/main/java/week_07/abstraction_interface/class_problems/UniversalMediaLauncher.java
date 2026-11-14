package week_07.abstraction_interface.class_problems;

interface Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class MediaFile {
    private static int nextId = 1000;
    private final String fileId;
    protected MediaFile() { fileId = "MF-" + (++nextId); }
    public abstract String getFormatInfo();
    public String getFileId() { return fileId; }
}

class AudioFile extends MediaFile implements Playable {
    private final String title;
    public AudioFile(String title) { this.title = title; }
    @Override public String getFormatInfo() { return "Audio file, ID: " + getFileId(); }
    @Override public String play() { return "Playing audio: " + title; }
    @Override public String play(int fromSecond) {
        return "Playing audio: " + title + " from " + (fromSecond / 60) + ":" + String.format("%02d", fromSecond % 60);
    }
    @Override public String pause() { return "Paused audio: " + title; }
}

class Podcast implements Playable {
    private final String showName;
    private final int episodeNumber;
    public Podcast(String showName, int episodeNumber) { this.showName = showName; this.episodeNumber = episodeNumber; }
    @Override public String play() { return "Streaming episode " + episodeNumber + " of " + showName; }
    @Override public String play(int fromSecond) {
        return "Streaming episode " + episodeNumber + " of " + showName + " from " + (fromSecond / 60) + ":" + String.format("%02d", fromSecond % 60);
    }
    @Override public String pause() { return "Paused episode " + episodeNumber + " of " + showName; }
}

public class UniversalMediaLauncher {
    static void launchAll(Playable[] items) { for (Playable item : items) System.out.println(item.play()); }
    public static void main(String[] args) {
        AudioFile a = new AudioFile("Morning Jazz");
        Podcast p = new Podcast("Tech Talk", 12);
        Playable ref = a; // Upcasting to the Playable interface.
        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());
        launchAll(new Playable[]{ref, p});
    }
}