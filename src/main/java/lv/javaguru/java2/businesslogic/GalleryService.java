package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;

import java.util.List;
import java.util.Optional;

public interface GalleryService {

    void addGallery(String title,
                    String description);

    List<Gallery> getAllGalleries();

    void removeGallery(String galleryTitle);

    Optional<Gallery> getGallery(String galleryTitle);
}
