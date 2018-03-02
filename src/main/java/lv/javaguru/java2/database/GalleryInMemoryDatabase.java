package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GalleryInMemoryDatabase implements GalleryDatabase {

    private List<Gallery> galleries = new ArrayList<>();

    @Override
    public void add(Gallery gallery) {
        galleries.add(gallery);
    }

    @Override
    public void addImageToGallery(Gallery gallery, String imageTitle) {
        Image image = new Image(imageTitle);
        gallery.addImageToGallery(image);
    }

    @Override
    public Optional<Gallery> findGalleryByTitle(String title) {
        return galleries.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public void remove(Gallery gallery) {
        galleries.remove(gallery);
    }

    @Override
    public void removeImageFromGallery(Gallery gallery, Image image) {
        gallery.getImages().remove(image);
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return new ArrayList<>(galleries);
    }

}
