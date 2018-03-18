package lv.javaguru.java2.views;

import lv.javaguru.java2.Image;
import lv.javaguru.java2.businesslogic.ImageService;
import lv.javaguru.java2.views.validation.UserInputValidator;

import java.util.Optional;
import java.util.Scanner;

public class GetAnImageFromAGalleryView implements View {
    private final ImageService imageService;
    private UserInputValidator validator;

    public GetAnImageFromAGalleryView(ImageService imageService, UserInputValidator validator) {
        this.imageService = imageService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Get image from gallery execution start!");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter gallery title:");
        String galleryTitle = sc.nextLine();
        System.out.print("Enter image title:");
        String imageTitle = sc.nextLine();
        Optional<Image> imageOptional = imageService.getImage(galleryTitle, imageTitle);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            System.out.println("Image: " + image.getTitle());
        } else {
            System.out.println("There is no such image");
        }

    }
}
