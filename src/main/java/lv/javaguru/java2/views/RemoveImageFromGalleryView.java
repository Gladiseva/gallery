package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.GalleryService;

import java.util.Scanner;

public class RemoveImageFromGalleryView implements View {
    private final GalleryService galleryService;

    public RemoveImageFromGalleryView(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove image from gallery execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter gallery title");
        String galleryTitle = sc.nextLine();
        System.out.print("Enter image title to remove:");
        String imageTitle = sc.nextLine();

        galleryService.removeImageFromGallery(galleryTitle, imageTitle);

        System.out.println("Remove image from a gallery execution end!");
        System.out.println();
    }
}
