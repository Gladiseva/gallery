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
    public void addImageToGallery(Gallery gallery, Image image) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO image(id, gallery_id, title, description) VALUES(default,?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, gallery.getId());
            preparedStatement.setString(2, image.getTitle());
            preparedStatement.setString(3, image.getDescription());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                image.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute addImageToGallery()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Gallery> findGalleryByTitle(String title) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM gallery WHERE title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            Gallery gallery = null;
            if (resultSet.next()) {
                gallery = new Gallery();
                gallery.setId(resultSet.getLong("id"));
                gallery.setTitle(resultSet.getString("title"));
                gallery.setDescription(resultSet.getString("description"));
            }
            return Optional.ofNullable(gallery);
        } catch (Throwable e) {
            System.out.println("Exception while execute findGalleryByTitle()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
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
