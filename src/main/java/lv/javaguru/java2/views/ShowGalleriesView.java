package lv.javaguru.java2.views;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.businesslogic.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShowGalleriesView implements View {
    @Autowired
    private GalleryService galleryService;

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print existing galleries to console execution start!");
        galleryService.getAllGalleries()
                .forEach(this::printGalleryOverview);
        System.out.println("Print existing galleries to console execution end!");
    }

    private void printGalleryOverview(Gallery gallery) {
        System.out.println(gallery.getTitle() + "[" + gallery.getDescription() + "]");
    }
}
