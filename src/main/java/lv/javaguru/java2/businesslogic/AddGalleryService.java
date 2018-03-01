package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.database.GalleryDatabase;

public class AddGalleryService {
    private GalleryDatabase galleryDatabase;

    public AddGalleryService(GalleryDatabase galleryDatabase) {
        this.galleryDatabase = galleryDatabase;
    }

    public void addGallery(String title,
                           String description) {
        Gallery gallery = new Gallery(title, description);
        galleryDatabase.add(gallery);
    }
}
