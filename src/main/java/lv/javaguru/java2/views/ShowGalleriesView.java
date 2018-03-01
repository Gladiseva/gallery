package lv.javaguru.java2.views;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.database.GalleryDatabase;

public class ShowGalleriesView implements View {
    private GalleryDatabase galleryDatabase;

    public ShowGalleriesView(GalleryDatabase galleryDatabase) {
        this.galleryDatabase = galleryDatabase;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print existing galleries to console execution start!");
        for (Gallery gallery : galleryDatabase.getAllGalleries()) {
            System.out.println(gallery.getTitle() + "[" + gallery.getDescription() + "]");
        }
        System.out.println("Print existing galleries to console execution end!");
    }
}
