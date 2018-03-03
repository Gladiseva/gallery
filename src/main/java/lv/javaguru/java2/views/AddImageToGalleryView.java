package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.ImageService;

import java.util.Scanner;

public class AddImageToGalleryView implements View {
    private final ImageService imageService;

    public AddImageToGalleryView(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add image to gallery execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title:");
        String galleryTitle = sc.nextLine();
        System.out.print("Enter image title:");
        String stringTitle = sc.nextLine();

        imageService.addImageToGallery(galleryTitle, stringTitle);

        System.out.println("Add image to gallery execution end!");
        System.out.println();
    }
}
