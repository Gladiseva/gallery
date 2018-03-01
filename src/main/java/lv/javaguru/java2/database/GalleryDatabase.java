package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.util.List;
import java.util.Optional;

public interface GalleryDatabase {
    void add(Gallery gallery);

    void addImageToGallery(Gallery gallery, String imageTitle);

    Optional<Gallery> findGalleryByTitle(String title);

    Optional<Image> findImageByTitle(String string);

    void remove(Gallery gallery);

    void remove(Image image);

    List<Gallery> getAllGalleries();

    List<Image> getAllImages(Gallery gallery);
}
