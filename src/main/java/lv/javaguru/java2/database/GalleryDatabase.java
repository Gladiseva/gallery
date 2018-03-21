package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.util.List;
import java.util.Optional;

public interface GalleryDatabase {
    void add(Gallery gallery);

    void addImageToGallery(Gallery gallery, Image image);

    Optional<Gallery> findGalleryByTitle(String title);

    Optional<Image> findImageByTitle(Gallery gallery, String imageTitle);

    void remove(Gallery gallery);

    void removeImageFromGallery(Gallery gallery, Image image);

    List<Gallery> getAllGalleries();

    List<Image> getAllImagesInAGallery(Gallery gallery);

}
