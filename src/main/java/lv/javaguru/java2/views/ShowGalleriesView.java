package lv.javaguru.java2.views;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.businesslogic.GalleryService;

public class ShowGalleriesView implements View {
    private final GalleryService galleryService;

    public ShowGalleriesView(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

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
