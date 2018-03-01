package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.util.List;

public interface GalleryService {
    void addGallery(String title,
                    String description);

    void addImageToGallery(String galleryTitle, String imageTitle);

    List<Image> getAllImagesInAGallery(String galleryTitle);

    List<Gallery> getAllGalleries();
}
