package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.database.GalleryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryDatabase galleryDatabase;

    @Override
    public void addGallery(String title,
                           String description) {
        Gallery gallery = new Gallery();
        gallery.setTitle(title);
        gallery.setDescription(description);
        galleryDatabase.add(gallery);
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return galleryDatabase.getAllGalleries();
    }

    @Override
    public void removeGallery(String galleryTitle) {
        Optional<Gallery> galleryOptional = getGallery(galleryTitle);
        galleryOptional
                .ifPresent(galleryDatabase::remove);
    }

    @Override
    public Optional<Gallery> getGallery(String galleryTitle) {
        return galleryDatabase.findGalleryByTitle(galleryTitle);
    }


}
