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
        Image image = new Image();
        image.setTitle(imageTitle);
        gallery.addImageToGallery(image);
    }

    @Override
    public Optional<Gallery> findGalleryByTitle(String title) {
        return galleries.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public Optional<Image> findImageByTitle(String imageTitle, String galleryTitle) {
        Optional<Gallery> galleryOptional = findGalleryByTitle(galleryTitle);
        if (galleryOptional.isPresent()) {
            Gallery gallery = galleryOptional.get();
            return gallery.getImages().stream()
                    .filter(image -> image.getTitle().equals(imageTitle))
                    .findFirst();

        }
        return Optional.empty();
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

    @Override
    public Optional<Gallery> findByTitle(String title) {
        return galleries.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
    }

}
