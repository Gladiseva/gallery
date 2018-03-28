package lv.javaguru.java2;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.views.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GalleryApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);


        Map<Integer, View> actionMap = new HashMap<>();
        actionMap.put(1, applicationContext.getBean(AddGalleryView.class));
        actionMap.put(2, applicationContext.getBean(AddImageToGalleryView.class));
        actionMap.put(3, applicationContext.getBean(ShowGalleriesView.class));
        actionMap.put(4, applicationContext.getBean(ShowImagesInAGalleryView.class));
        actionMap.put(5, applicationContext.getBean(RemoveGalleryView.class));
        actionMap.put(6, applicationContext.getBean(RemoveImageFromGalleryView.class));
        actionMap.put(7, applicationContext.getBean(GetAnImageFromAGalleryView.class));
        actionMap.put(8, applicationContext.getBean(ProgramExitView.class));

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

