package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.Optional;

public class ImageServiceImpl implements ImageService {

    private final GalleryService galleryService;
    private final GalleryDatabase galleryDatabase;

    public ImageServiceImpl(GalleryService galleryService, GalleryDatabase galleryDatabase) {
        this.galleryService = galleryService;
        this.galleryDatabase = galleryDatabase;
    }

    @Override
    public void addImageToGallery(String galleryTitle, String imageTitle) {
        Optional<Gallery> galleryOptional = galleryService.getGallery(galleryTitle);
        galleryOptional
                .ifPresent(gallery -> galleryDatabase.addImageToGallery(gallery, imageTitle));
    }
}
