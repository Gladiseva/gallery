package lv.javaguru.java2.views;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.businesslogic.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;


@Component
public class GetGalleryView implements View {
    @Autowired
    private GalleryService galleryService;


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
