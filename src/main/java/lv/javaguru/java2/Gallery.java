package lv.javaguru.java2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gallery {
    private Long id;
    private String title;
    private String description;

    private List<Image> images = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImageToGallery(Image image) {
        images.add(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery gallery = (Gallery) o;
        return Objects.equals(id, gallery.id) &&
                Objects.equals(title, gallery.title) &&
                Objects.equals(description, gallery.description) &&
                Objects.equals(images, gallery.images);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, images);
    }


}


