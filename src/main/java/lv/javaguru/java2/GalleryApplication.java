package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.businesslogic.GalleryServiceImpl;
import lv.javaguru.java2.database.GalleryDatabase;
import lv.javaguru.java2.database.GalleryInMemoryDatabase;
import lv.javaguru.java2.views.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GalleryApplication {

    public static void main(String[] args) {

        GalleryDatabase database = new GalleryInMemoryDatabase();
        GalleryService galleryService = new GalleryServiceImpl(database);

        View addGalleryView = new AddGalleryView(galleryService);
        View addImageToGalleryView = new AddImageToGalleryView(galleryService);
        View showGalleriesView = new ShowGalleriesView(galleryService);
        View showImagesView = new ShowImagesInAGalleryView(galleryService);
        View removeGalleryView = new RemoveGalleryView(galleryService);
        View removeImageFromGalleryView = new RemoveImageFromGalleryView(galleryService);
        View getAnImageFromAGalleryView = new GetAnImageFromAGalleryView(galleryService);

        Map<Integer, View> actionMap = new HashMap<>();
        actionMap.put(1, addGalleryView);
        actionMap.put(2, addImageToGalleryView);
        actionMap.put(3, showGalleriesView);
        actionMap.put(4, showImagesView);
        actionMap.put(5, removeGalleryView);
        actionMap.put(6, removeImageFromGalleryView);
        actionMap.put(7, getAnImageFromAGalleryView);

        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            View view = actionMap.get(menuItem);
            view.execute();
        }

    }

    private static void printProgramMenu() {
        System.out.println("Program Menu:");
        System.out.println("1. Add Gallery");
        System.out.println("2. Add Image to Gallery");
        System.out.println("3. Show available galleries");
        System.out.println("4. Show all images in a specific gallery");
        System.out.println("5. Remove gallery");
        System.out.println("6. Remove image from gallery");
        System.out.println("7. Get an image from a gallery");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}

