package lv.javaguru.java2.views;

import lv.javaguru.java2.Image;
import lv.javaguru.java2.businesslogic.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;


@Component
public class GetAnImageFromAGalleryView implements View {
    @Autowired
    private ImageService imageService;


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
