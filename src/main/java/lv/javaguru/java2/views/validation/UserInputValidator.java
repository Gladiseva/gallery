package lv.javaguru.java2.views.validation;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.businesslogic.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserInputValidator {
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private ImageService imageService;

    public List<ValidationError> validateGalleryCreate(String title) {
        List<ValidationError> errors = new ArrayList<>();
        validateTitle(title).ifPresent(errors::add);
        validateGalleryDuplicateTitle(title).ifPresent(errors::add);
        return errors;
    }

    public List<ValidationError> validateGalleryUpdate(String title) {
        List<ValidationError> errors = new ArrayList<>();
        validateTitle(title).ifPresent(errors::add);
        validateGalleryExistence(title).ifPresent(errors::add);
        return errors;
    }

    public List<ValidationError> validateImageCreate(String imageTitle, String galleryTitle) {
        List<ValidationError> errors = new ArrayList<>();
        validateGalleryExistence(galleryTitle).ifPresent(errors::add);
        validateTitle(imageTitle).ifPresent(errors::add);
        validateImageDuplicateTitle(imageTitle, galleryTitle).ifPresent(errors::add);
        return errors;
    }

    public List<ValidationError> validateImageUpdate(String imageTitle, String galleryTitle) {
        List<ValidationError> errors = new ArrayList<>();
        validateTitle(imageTitle).ifPresent(errors::add);
        validateImageExistence(imageTitle, galleryTitle).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Optional.of(new ValidationError("title", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateGalleryDuplicateTitle(String title) {
        if (title != null && !title.isEmpty()) {
            Optional<Gallery> galleryOptional = galleryService.getGallery(title);
            if (galleryOptional.isPresent()) {
                return Optional.of(new ValidationError("Gallery title", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateImageDuplicateTitle(String imageTitle, String galleryTitle) {
        Optional<Image> imageOptional = imageService.getImage(imageTitle, galleryTitle);

        if (imageOptional.isPresent()) {
            return Optional.of(new ValidationError("Image title", "Must not be repeated"));
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateGalleryExistence(String title) {
        if (title != null && !title.isEmpty()) {
            Optional<Gallery> galleryOptional = galleryService.getGallery(title);
            if (!(galleryOptional.isPresent())) {
                return Optional.of(new ValidationError("title", "There is no such gallery"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateImageExistence(String imageTitle, String galleryTitle) {
        if (imageTitle != null && !imageTitle.isEmpty()) {
            Optional<Image> imageOptional = imageService.getImage(imageTitle, galleryTitle);
            if (!(imageOptional.isPresent())) {
                return Optional.of(new ValidationError("title", "There is no such image"));
            }
        }
        return Optional.empty();
    }

}
