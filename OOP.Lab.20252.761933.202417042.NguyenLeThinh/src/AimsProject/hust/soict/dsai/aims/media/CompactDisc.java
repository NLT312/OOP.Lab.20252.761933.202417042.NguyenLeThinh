package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;

public class CompactDisc extends Media implements Playable {
    private String artist;
    private String[] tracks;

    public CompactDisc(String title, String category, String artist, float cost, String[] tracks) {
        super(title, category, cost);
        this.artist = artist;
        this.tracks = tracks;
    }

    public String getArtist() { return artist; }
    public String[] getTracks() { return tracks; }

    @Override
    public String toString() {
        return String.format("CD - %s - %s by %s: %.2f $", getTitle(), getCategory(), artist, getCost());
    }
    //

    @Override
    public void play() {
        System.out.println("Playing CD by " + artist);
        System.out.println("CD title: " + getTitle());
    }
    //
}
