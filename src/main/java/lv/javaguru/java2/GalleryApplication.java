package lv.javaguru.java2;

import lv.javaguru.java2.database.GalleryDatabase;
import lv.javaguru.java2.database.GalleryInMemoryDatabase;
import lv.javaguru.java2.views.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GalleryApplication {

    public static void main(String[] args) {

        GalleryDatabase database = new GalleryInMemoryDatabase();

        View addGalleryView = new AddGalleryView(database);
        View addImageToGalleryView = new AddImageToGalleryView(database);
        View showGalleriesView = new ShowGalleriesView(database);
        View showImagesView = new ShowImagesInAGalleryView(database);

        Map<Integer, View> actionMap = new HashMap<>();
        actionMap.put(1, addGalleryView);
        actionMap.put(2, addImageToGalleryView);
        actionMap.put(3, showGalleriesView);
        actionMap.put(4, showImagesView);

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
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}

