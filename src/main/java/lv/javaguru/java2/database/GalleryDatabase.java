package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.util.List;
import java.util.Optional;

public interface GalleryDatabase {
    void add(Gallery gallery);

    void addImageToGallery(Gallery gallery, String imageTitle);

    Optional<Gallery> findGalleryByTitle(String title);

    Optional<Image> findImageByTitle(String imageTitle, String galleryTitle);

    void remove(Gallery gallery);

    void removeImageFromGallery(Gallery gallery, Image image);

    List<Gallery> getAllGalleries();

    Optional<Gallery> findByTitle(String title);

}
