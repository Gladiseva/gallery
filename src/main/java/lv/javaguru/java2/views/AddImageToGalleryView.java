package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.ImageService;
import lv.javaguru.java2.views.validation.UserInputValidator;
import lv.javaguru.java2.views.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AddImageToGalleryView implements View {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserInputValidator validator;

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
