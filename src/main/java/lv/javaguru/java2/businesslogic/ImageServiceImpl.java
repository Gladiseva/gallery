package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import lv.javaguru.java2.database.GalleryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    private GalleryService galleryService;
    @Autowired
    private GalleryDatabase galleryDatabase;

    @Override
    @Transactional
    public void addImageToGallery(String galleryTitle, String imageTitle) {
        Image image = new Image();
        image.setTitle(imageTitle);
        Optional<Gallery> galleryOptional = galleryService.getGallery(galleryTitle);
        galleryOptional
                .ifPresent(gallery -> galleryDatabase.addImageToGallery(image, gallery));
    }

    @Override
    @Transactional
    public Optional<Image> getImage(String galleryTitle, String imageTitle) {
        Optional<Gallery> galleryOptional = galleryService.getGallery(galleryTitle);
        if (galleryOptional.isPresent()) {
            return galleryDatabase.findImageByTitle(imageTitle);
        } else {
            return Optional.empty();
        }
    }


    @Override
    @Transactional
    public List<Image> getAllImagesInAGallery(String galleryTitle) {
        Optional<Gallery> galleryOptional = galleryService.getGallery(galleryTitle);
        if (galleryOptional.isPresent()) {
            Gallery galleryToFind = galleryOptional.get();
            return galleryDatabase.getAllImagesInAGallery(galleryToFind);
        } else {
            return new ArrayList<>();
        }

    }


    @Override
    @Transactional
    public void removeImageFromGallery(String galleryTitle, String imageTitle) {
        galleryService.getGallery(galleryTitle)
                .ifPresent(gallery -> removeImageFromGallery(galleryTitle, imageTitle, gallery));
    }

    private void removeImageFromGallery(String galleryTitle, String imageTitle, Gallery gallery) {
        getAllImagesInAGallery(galleryTitle).stream()
                .filter(image -> image.getTitle().equals(imageTitle))
                .findFirst()
                .ifPresent(imageToDelete -> galleryDatabase.removeImageFromGallery(imageToDelete));
    }
}
