package lv.javaguru.java2.views;

import lv.javaguru.java2.Image;
import lv.javaguru.java2.businesslogic.ImageService;
import lv.javaguru.java2.views.validation.UserInputValidator;
import lv.javaguru.java2.views.validation.ValidationError;

import java.util.List;
import java.util.Scanner;

public class ShowImagesInAGalleryView implements View {
    private final ImageService imageService;
    private UserInputValidator validator;

    public ShowImagesInAGalleryView(ImageService imageService, UserInputValidator validator) {
        this.imageService = imageService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print existing images in a gallery to console execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title:");
        String title = sc.nextLine();

        List<ValidationError> errors = validator.validateGallery(title);

        if (errors.isEmpty()) {
            List<Image> images = imageService.getAllImagesInAGallery("galleryTitle");
            for (Image image : images) {
                System.out.println(image.getTitle());
            }
            System.out.println("Print existing images in a gallery to console execution end!");
        } else {
            errors.forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
            System.out.println();
        }


    }


}
