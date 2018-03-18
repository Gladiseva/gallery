package lv.javaguru.java2.views;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.views.validation.UserInputValidator;

import java.util.Optional;
import java.util.Scanner;

public class GetGalleryView implements View {
    private final GalleryService galleryService;
    private UserInputValidator validator;

    public GetGalleryView(GalleryService galleryService, UserInputValidator validator) {
        this.galleryService = galleryService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Get gallery execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title:");
        String galleryTitle = sc.nextLine();
        Optional<Gallery> galleryOptional = galleryService.getGallery(galleryTitle);
        if (galleryOptional.isPresent()) {
            Gallery gallery = galleryOptional.get();
            System.out.println("Gallery: " + gallery.getTitle());
            System.out.println();
        } else {
            System.out.println("There is no such gallery");
        }

    }
}
