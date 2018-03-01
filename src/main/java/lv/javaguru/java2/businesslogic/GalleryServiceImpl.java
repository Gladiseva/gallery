package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GalleryServiceImpl implements GalleryService {
    private final GalleryDatabase galleryDatabase;

    public GalleryServiceImpl(GalleryDatabase galleryDatabase) {
        this.galleryDatabase = galleryDatabase;
    }

    @Override
    public void addGallery(String title,
                           String description) {
        Gallery gallery = new Gallery(title, description);
        galleryDatabase.add(gallery);
    }

    @Override
    public void addImageToGallery(String galleryTitle, String imageTitle) {
        Optional<Gallery> galleryOptional = getGallery(galleryTitle);
        galleryOptional
                .ifPresent(gallery -> galleryDatabase.addImageToGallery(gallery, imageTitle));
    }

    @Override
    public List<Image> getAllImagesInAGallery(String galleryTitle) {
        return getGallery(galleryTitle)
                .map(Gallery::getImages)
                .orElse(new ArrayList<>());
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return galleryDatabase.getAllGalleries();
    }

    private Optional<Gallery> getGallery(String galleryTitle) {
        return galleryDatabase.findGalleryByTitle(galleryTitle);
    }
}
