package lv.javaguru.java2.views;

import lv.javaguru.java2.Image;
import lv.javaguru.java2.businesslogic.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


@Component
public class ShowImagesInAGalleryView implements View {
    @Autowired
    private ImageService imageService;

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print existing images in a gallery to console execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title:");
        String title = sc.nextLine();

        List<Image> images = imageService.getAllImagesInAGallery(title);
        if (images.size() > 0) {
            for (Image image : images) {
                System.out.println(image.getTitle());
            }
        } else {
            System.out.println("There is no images in a gallery");
        }


    }


}
