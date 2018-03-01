package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.List;
import java.util.Optional;

public class ShowImagesInAGalleryService {
    private GalleryDatabase galleryDatabase;

    public ShowImagesInAGalleryService(GalleryDatabase galleryDatabase) {
        this.galleryDatabase = galleryDatabase;
    }

    private Optional<Gallery> getGallery (String galleryTitle){
        return galleryDatabase.findGalleryByTitle(galleryTitle);
    }

    public List<Image> getAllImagesInAGallery(String galleryTitle){
        Optional<Gallery> foundGallery = getGallery(galleryTitle);
        if (foundGallery.isPresent()) {
            Gallery gallery = foundGallery.get();
            return gallery.getImages();
        } else {
            return null;
        }

    }
}
