package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class GalleryRealDatabase extends JDBCDatabase implements GalleryDatabase {
    @Override
    public void add(Gallery gallery) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into gallery(id, title, description) values(default, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, gallery.getTitle());
            preparedStatement.setString(2, gallery.getDescription());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                gallery.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute add()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void addImageToGallery(Gallery gallery, String imageTitle) {

    }

    @Override
    public Optional<Gallery> findGalleryByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Image> findImageByTitle(String imageTitle, String galleryTitle) {
        return Optional.empty();
    }

    @Override
    public void remove(Gallery gallery) {

    }

    @Override
    public void removeImageFromGallery(Gallery gallery, Image image) {

    }

    @Override
    public List<Gallery> getAllGalleries() {
        return null;
    }

    @Override
    public Optional<Gallery> findByTitle(String title) {
        return Optional.empty();
    }
}
