package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.GalleryService;

import java.util.Scanner;

public class AddGalleryView implements View {

    private final GalleryService galleryService;

    public AddGalleryView(GalleryService galleryService) {
        this.galleryService = galleryService;
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

        galleryService.addGallery(title, description);

        System.out.println("Add product to list execution end!");
        System.out.println();
    }
}
