package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.views.validation.UserInputValidator;
import lv.javaguru.java2.views.validation.ValidationError;

import java.util.List;
import java.util.Scanner;

public class AddGalleryView implements View {

    private final GalleryService galleryService;
    private UserInputValidator validator;

    public AddGalleryView(GalleryService galleryService, UserInputValidator validator) {
        this.galleryService = galleryService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add gallery execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title:");
        String title = sc.nextLine();
        System.out.print("Enter gallery description:");
        String description = sc.nextLine();
        List<ValidationError> errors = validator.validateInputTitle(title);
        if (errors.isEmpty()) {
            galleryService.addGallery(title, description);
            System.out.println("Gallery successfully added");
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
