// File path: AimsProject/src/hust/soict/hedspi/aims/media/Media.java
package hust.soict.hedspi.aims.media;

import java.util.Objects;

public abstract class Media implements Comparable<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) return 1;
        if (this.title == null && other.title == null) return 0;
        if (this.title == null) return -1;
        if (other.title == null) return 1;
        
        int titleCompare = this.title.compareToIgnoreCase(other.title);
        if (titleCompare != 0) return titleCompare;
        
        if (this.category == null && other.category == null) return 0;
        if (this.category == null) return -1;
        if (other.category == null) return 1;
        
        return this.category.compareToIgnoreCase(other.category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media)) return false;
        Media media = (Media) o;
        if (this.title == null || media.title == null) return false;
        return this.title.equalsIgnoreCase(media.title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title == null ? null : title.toLowerCase());
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", cost=" + cost +
                '}';
    }
}
