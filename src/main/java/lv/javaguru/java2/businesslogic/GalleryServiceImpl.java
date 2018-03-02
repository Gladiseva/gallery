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

    @Override
    public void removeGallery(String galleryTitle) {
        Optional<Gallery> galleryOptional = getGallery(galleryTitle);
        galleryOptional
                .ifPresent(galleryDatabase::remove);
    }

    @Override
    public void removeImageFromGallery(String galleryTitle, String imageTitle) {
        Gallery gallery;
        Image imageToDelete;
        Optional<Gallery> galleryOptional = getGallery(galleryTitle);
        if (galleryOptional.isPresent()) {
            gallery = galleryOptional.get();
            List<Image> images = getAllImagesInAGallery(galleryTitle);
            Optional<Image> imageOptional = images.stream()
                    .filter(image -> image.getTitle().equals(imageTitle))
                    .findFirst();
            if (imageOptional.isPresent()) {
                imageToDelete = imageOptional.get();
                galleryDatabase.removeImageFromGallery(gallery, imageToDelete);
            }
        }
    }

    @Override
    public Optional<Image> getImage(String galleryTitle, String imageTitle) {
        Gallery galleryToFind;
        Optional<Gallery> galleryOptional = getGallery(galleryTitle);
        if (galleryOptional.isPresent()) {
            galleryToFind = galleryOptional.get();
            return galleryToFind.getImages().stream().filter(image -> image.getTitle().equals(imageTitle)).findFirst();
        } else {
            return Optional.empty();
        }
    }

    private Optional<Gallery> getGallery(String galleryTitle) {
        return galleryDatabase.findGalleryByTitle(galleryTitle);
    }
}
