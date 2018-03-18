package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.util.List;
import java.util.Optional;

public class ProductRealDatabase extends JDBCDatabase implements GalleryDatabase {
    @Override
    public void add(Gallery gallery) {
        
    }

    @Override
    public void addImageToGallery(Gallery gallery, String imageTitle) {

    }

    @Override
    public Optional<Gallery> findGalleryByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Image> findImageByTitle(String imageTitle, String galleryTitle) {
        return Optional.empty();
    }

    @Override
    public void remove(Gallery gallery) {

    }

    @Override
    public void removeImageFromGallery(Gallery gallery, Image image) {

    }

    @Override
    public List<Gallery> getAllGalleries() {
        return null;
    }

    @Override
    public Optional<Gallery> findByTitle(String title) {
        return Optional.empty();
    }
}
