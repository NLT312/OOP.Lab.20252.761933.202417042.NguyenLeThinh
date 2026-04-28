package hust.soict.dsai.aims.media;

public class Book extends Media {
    private String author;
    private String content;

    public Book(String title, String category, String author, float cost) {
        super(title, category, cost);
        this.author = author;
        this.content = "";
    }

    public String getAuthor() { return author; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return String.format("Book - %s - %s by %s: %.2f $", getTitle(), getCategory(), author, getCost());
    }
    //
}
