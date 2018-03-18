package lv.javaguru.java2.views;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.views.validation.UserInputValidator;

import java.util.Optional;
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
        Optional<Gallery> galleryOptional = galleryService.getGallery(title);
        if (galleryOptional.isPresent()) {
            galleryService.removeGallery(title);
        } else {
            System.out.println("There is no such gallery");
        }

    }

}
