package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.util.List;
import java.util.Optional;

public interface GalleryService {
    void addGallery(String title,
                    String description);

    void addImageToGallery(String galleryTitle, String imageTitle);

    List<Image> getAllImagesInAGallery(String galleryTitle);

    List<Gallery> getAllGalleries();

    void removeGallery(String galleryTitle);

    void removeImageFromGallery(String galleryTitle, String imageTitle);

    Optional<Image> getImage(String galleryTitle, String imageTitle);

}
