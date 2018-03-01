package lv.javaguru.java2.views;

import lv.javaguru.java2.Image;
import lv.javaguru.java2.businesslogic.GalleryService;

import java.util.List;
import java.util.Scanner;

public class ShowImagesInAGalleryView implements View {
    private final GalleryService galleryService;

    public ShowImagesInAGalleryView(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print existing images in a gallery to console execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title:");
        String galleryTitle = sc.nextLine();

        List<Image> images = galleryService.getAllImagesInAGallery(galleryTitle);


        for (Image image : images) {
            System.out.println(image.getTitle());
        }
        System.out.println("Print existing images in a gallery to console execution end!");
    }
}
