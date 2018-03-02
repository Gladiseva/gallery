package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.GalleryService;

import java.util.Scanner;

public class RemoveGalleryView implements View {
    private final GalleryService galleryService;

    public RemoveGalleryView(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove gallery execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title to remove:");
        String title = sc.nextLine();

        galleryService.removeGallery(title);

        System.out.println("Remove gallery execution end!");
        System.out.println();
    }
}
