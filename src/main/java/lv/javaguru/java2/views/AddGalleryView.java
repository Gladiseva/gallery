package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.AddGalleryService;
import lv.javaguru.java2.database.GalleryDatabase;

import java.util.Scanner;

public class AddGalleryView implements View {

    private AddGalleryService addGalleryService;

    public AddGalleryView(GalleryDatabase database) {
        this.addGalleryService = new AddGalleryService(database);
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

        addGalleryService.addGallery(title, description);

        System.out.println("Add product to list execution end!");
        System.out.println();
    }
}
