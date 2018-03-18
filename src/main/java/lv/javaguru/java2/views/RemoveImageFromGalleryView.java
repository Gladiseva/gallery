package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.ImageService;
import lv.javaguru.java2.views.validation.UserInputValidator;
import lv.javaguru.java2.views.validation.ValidationError;

import java.util.List;
import java.util.Scanner;


public class RemoveImageFromGalleryView implements View {
    private final ImageService imageService;
    private UserInputValidator validator;

    public RemoveImageFromGalleryView(ImageService imageService, UserInputValidator validator) {
        this.imageService = imageService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove image from gallery execution start!");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter gallery title:");
        String galleryTitle = sc.nextLine();
        List<ValidationError> galleryErrors = validator.validateGallery(galleryTitle);
        System.out.print("Enter image title:");
        String imageTitle = sc.nextLine();
        List<ValidationError> imageErrors = validator.validateImage(imageTitle, galleryTitle);

        if (galleryErrors.isEmpty() && imageErrors.isEmpty()) {
            imageService.removeImageFromGallery(galleryTitle, imageTitle);
            System.out.println("Image successfully removed from gallery!");
            System.out.println();
        } else {
            galleryErrors.forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
            imageErrors.forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
            System.out.println();
        }
    }
}
