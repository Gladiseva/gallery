package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.GalleryService;
import lv.javaguru.java2.businesslogic.GalleryServiceImpl;
import lv.javaguru.java2.businesslogic.ImageService;
import lv.javaguru.java2.businesslogic.ImageServiceImpl;
import lv.javaguru.java2.database.GalleryDatabase;
import lv.javaguru.java2.database.GalleryRealDatabase;
import lv.javaguru.java2.views.*;
import lv.javaguru.java2.views.validation.UserInputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GalleryApplication {

    public static void main(String[] args) {

        GalleryDatabase database = new GalleryRealDatabase();
        GalleryService galleryService = new GalleryServiceImpl(database);
        ImageService imageService = new ImageServiceImpl(galleryService, database);
        UserInputValidator validator = new UserInputValidator(galleryService, imageService);

        View addGalleryView = new AddGalleryView(galleryService, validator);
        View addImageToGalleryView = new AddImageToGalleryView(imageService, galleryService, validator);
        View showGalleriesView = new ShowGalleriesView(galleryService);
        View showImagesView = new ShowImagesInAGalleryView(imageService, validator);
        View removeGalleryView = new RemoveGalleryView(galleryService, validator);
        View removeImageFromGalleryView = new RemoveImageFromGalleryView(imageService, validator);
        View getAnImageFromAGalleryView = new GetAnImageFromAGalleryView(imageService, validator);
        View programExitView = new ProgramExitView();

        Map<Integer, View> actionMap = new HashMap<>();
        actionMap.put(1, addGalleryView);
        actionMap.put(2, addImageToGalleryView);
        actionMap.put(3, showGalleriesView);
        actionMap.put(4, showImagesView);
        actionMap.put(5, removeGalleryView);
        actionMap.put(6, removeImageFromGalleryView);
        actionMap.put(7, getAnImageFromAGalleryView);
        actionMap.put(8, programExitView);

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
        System.out.println("8. Quit");

    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}

