package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        super();
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
        this.setTitle(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super();
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super();
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
        this.setTitle(title);
        this.setCategory(category);
        this.setDirector(director);
        this.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
        this.setTitle(title);
        this.setCategory(category);
        this.setDirector(director);
        this.setLength(length);
        this.setCost(cost);
    }

    public DigitalVideoDisc(int id, String title, String category,
                            float cost, int length, String director) {
        super(id, title, category, cost, length, director);
    }

    public boolean isMatch(String title) {
        return this.getTitle().equalsIgnoreCase(title);
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play DVD: " + this.getTitle());
            return;
        }

        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle()
                + " - " + getCategory()
                + " - " + getDirector()
                + " - " + getLength()
                + ": " + getCost() + "$";
    }
}