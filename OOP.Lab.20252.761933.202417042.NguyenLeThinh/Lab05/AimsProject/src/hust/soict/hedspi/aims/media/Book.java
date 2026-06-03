// File path: AimsProject/src/hust/soict/hedspi/aims/media/Book.java
package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<>();

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public List<String> getAuthors() {
        return new ArrayList<>(authors);
    }

    public void addAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            return;
        }
        String normalized = authorName.trim();
        if (!authors.contains(normalized)) {
            authors.add(normalized);
        }
    }

    public void removeAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            return;
        }
        authors.remove(authorName.trim());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", authors=" + authors +
                ", cost=" + getCost() +
                '}';
    }
}
