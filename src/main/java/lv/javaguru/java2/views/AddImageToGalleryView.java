package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.AddImageToGalleryService;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.Scanner;

public class AddImageToGalleryView implements View {
    private AddImageToGalleryService addImageToGalleryService;

    public AddImageToGalleryView(GalleryDatabase database) {
        this.addImageToGalleryService = new AddImageToGalleryService(database);
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

        addImageToGalleryService.addImageToGallery(galleryTitle, stringTitle);

        System.out.println("Add image to gallery execution end!");
        System.out.println();
    }
}
