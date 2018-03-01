package lv.javaguru.java2;

import java.util.ArrayList;
import java.util.List;

public class Gallery {
    private String title;
    private String description;

    private List<Image> images =new ArrayList<>();

    public Gallery(String title, String description) {
        this.title = title;
        this.description = description;
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

    public void addImageToGallery(Image image) {
        images.add(image);
    }
}
