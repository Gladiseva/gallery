package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ORMDatabase implements GalleryDatabase {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Gallery gallery) {
        session().save(gallery);
    }

    @Override
    public void addImageToGallery(Image image, Gallery gallery) {
        image.setGalleryId(gallery.getId());
        session().save(image);
    }

    @Override
    public Optional<Gallery> findGalleryByTitle(String title) {
        Gallery gallery = (Gallery) session().createCriteria(Gallery.class)
                .add(Restrictions.eq("title", title))
                .uniqueResult();
        return Optional.ofNullable(gallery);
    }

    @Override
    public Optional<Image> findImageByTitle(String imageTitle) {
        Image image = (Image) session().createCriteria(Image.class)
                .add(Restrictions.eq("title", imageTitle))
                .uniqueResult();
        return Optional.ofNullable(image);
    }

    @Override
    public void remove(Gallery gallery) {
        session().delete(gallery);
    }

    @Override
    public void removeImageFromGallery(Image image) {
        session().delete(image);
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return session()
                .createCriteria(Gallery.class)
                .list();
    }

    @Override
    public List<Image> getAllImagesInAGallery(Gallery gallery) {
        return session()
                .createCriteria(Image.class)
                .add(Restrictions.eq("galleryId", gallery.getId()))
                .list();
    }
}
