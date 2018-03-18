package lv.javaguru.java2.views.validation;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInputValidator {
    private GalleryDatabase galleryDatabase;

    public UserInputValidator(GalleryDatabase galleryDatabase) {
        this.galleryDatabase = galleryDatabase;
    }

    public List<ValidationError> validateInputTitle(String title) {
        List<ValidationError> errors = new ArrayList<>();
        validateTitle(title).ifPresent(errors::add);
        validateDuplicateTitle(title).ifPresent(errors::add);
        return errors;
    }

    public List<ValidationError> validateImage(String imageTitle, String galleryTitle) {
        List<ValidationError> errors = new ArrayList<>();
        validateTitle(imageTitle).ifPresent(errors::add);
        validateImageExistence(imageTitle, galleryTitle).ifPresent(errors::add);
        return errors;
    }

    public List<ValidationError> validateGallery(String title) {
        List<ValidationError> errors = new ArrayList<>();
        validateTitle(title).ifPresent(errors::add);
        validateGalleryExistence(title).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Optional.of(new ValidationError("title", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicateTitle(String title) {
        if (title != null && !title.isEmpty()) {
            Optional<Gallery> galleryOptional = galleryDatabase.findByTitle(title);
            if (galleryOptional.isPresent()) {
                return Optional.of(new ValidationError("title", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateGalleryExistence(String title) {
        if (title != null && !title.isEmpty()) {
            Optional<Gallery> galleryOptional = galleryDatabase.findByTitle(title);
            if (!(galleryOptional.isPresent())) {
                return Optional.of(new ValidationError("title", "There is no such gallery"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateImageExistence(String imageTitle, String galleryTitle) {
        if (imageTitle != null && !imageTitle.isEmpty()) {
            Optional<Image> imageOptional = galleryDatabase.findImageByTitle(imageTitle, galleryTitle);
            if (!(imageOptional.isPresent())) {
                return Optional.of(new ValidationError("title", "There is no such image"));
            }
        }
        return Optional.empty();
    }

}
