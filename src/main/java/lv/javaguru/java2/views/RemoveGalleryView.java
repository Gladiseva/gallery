package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.views.validation.UserInputValidator;
import lv.javaguru.java2.views.validation.ValidationError;

import java.util.List;
import java.util.Scanner;


public class RemoveGalleryView implements View {
    private final GalleryService galleryService;
    private UserInputValidator validator;

    public RemoveGalleryView(GalleryService galleryService, UserInputValidator validator) {
        this.galleryService = galleryService;
        this.validator = validator;

    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove gallery execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title:");
        String title = sc.nextLine();
        List<ValidationError> errors = validator.validateGallery(title);
        if (errors.isEmpty()) {
            galleryService.removeGallery(title);
            System.out.println("Gallery successfully removed");
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
