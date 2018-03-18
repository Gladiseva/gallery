package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Optional<Image> getImage(String galleryTitle, String imageTitle) {
        Optional<Gallery> galleryOptional = galleryService.getGallery(galleryTitle);
        if (galleryOptional.isPresent()) {
            Gallery galleryToFind = galleryOptional.get();
            return galleryToFind.getImages().stream()
                    .filter(image -> image.getTitle().equals(imageTitle))
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }


    @Override
    public List<Image> getAllImagesInAGallery(String galleryTitle) {
        return galleryService.getGallery(galleryTitle)
                .map(Gallery::getImages)
                .orElse(new ArrayList<>());
    }


    @Override
    public void removeImageFromGallery(String galleryTitle, String imageTitle) {
        galleryService.getGallery(galleryTitle)
                .ifPresent(gallery -> removeImageFromGallery(galleryTitle, imageTitle, gallery));
    }

    private void removeImageFromGallery(String galleryTitle, String imageTitle, Gallery gallery) {
        getAllImagesInAGallery(galleryTitle).stream()
                .filter(image -> image.getTitle().equals(imageTitle))
                .findFirst()
                .ifPresent(imageToDelete -> galleryDatabase.removeImageFromGallery(gallery, imageToDelete));
    }
}
