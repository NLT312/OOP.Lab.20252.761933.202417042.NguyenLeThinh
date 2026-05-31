package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<String>();

    public Book() {
        super();
    }

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Author added: " + authorName);
        } else {
            System.out.println("Author already exists: " + authorName);
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Author removed: " + authorName);
        } else {
            System.out.println("Author does not exist: " + authorName);
        }
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory()
                + " - Authors: " + authors
                + " - " + getCost() + "$";
    }
}