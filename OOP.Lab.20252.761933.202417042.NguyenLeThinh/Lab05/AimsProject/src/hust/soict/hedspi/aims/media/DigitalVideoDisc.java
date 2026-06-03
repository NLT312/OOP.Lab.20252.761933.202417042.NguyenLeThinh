// File path: AimsProject/src/hust/soict/hedspi/aims/media/DigitalVideoDisc.java
package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Media implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
        this.director = "Unknown";
        this.length = 0;
    }

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("- Director: " + director);
        System.out.println("- Category: " + getCategory());
        System.out.println("- Length: " + length + " minutes");
        System.out.println("- Cost: $" + getCost());
    }

    @Override
    public String toString() {
        return "DigitalVideoDisc{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", director='" + director + '\'' +
                ", length=" + length +
                ", cost=" + getCost() +
                '}';
    }
}
