package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.businesslogic.ImageService;
import lv.javaguru.java2.views.validation.UserInputValidator;
import lv.javaguru.java2.views.validation.ValidationError;

import java.util.List;
import java.util.Scanner;

public class AddImageToGalleryView implements View {
    private final ImageService imageService;
    private final GalleryService galleryService;
    private UserInputValidator validator;

    public AddImageToGalleryView(ImageService imageService, GalleryService galleryService, UserInputValidator validator) {
        this.imageService = imageService;
        this.galleryService = galleryService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add image to gallery execution start!");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter gallery title:");
        String galleryTitle = sc.nextLine();
        System.out.print("Enter image title:");
        String imageTitle = sc.nextLine();
        List<ValidationError> errors = validator.validateImageCreate(imageTitle, galleryTitle);

        if (errors.isEmpty()) {
            imageService.addImageToGallery(galleryTitle, imageTitle);
            System.out.println("Image successfully added to gallery!");
            System.out.println();
        } else {
            errors.forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
            System.out.println();
        }

    }
}
