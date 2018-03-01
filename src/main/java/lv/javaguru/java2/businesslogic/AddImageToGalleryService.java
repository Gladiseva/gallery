package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.Optional;

public class AddImageToGalleryService {
    private GalleryDatabase galleryDatabase;

    public AddImageToGalleryService(GalleryDatabase galleryDatabase) {
        this.galleryDatabase = galleryDatabase;
    }

    private Gallery selectGallery(String title) {
        Optional<Gallery> foundGallery = galleryDatabase.findGalleryByTitle(title);
        if (foundGallery.isPresent()) {
            return foundGallery.get();
        } else {
            return null;
        }
    }

    public void addImageToGallery(String galleryTitle, String imageTitle) {
        Gallery gallery = selectGallery(galleryTitle);
        galleryDatabase.addImageToGallery(gallery, imageTitle);

    }
}
