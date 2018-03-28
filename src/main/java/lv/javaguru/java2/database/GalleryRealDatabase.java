package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
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
    public Optional<Image> findImageByTitle(Gallery gallery, String imageTitle) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM image WHERE gallery_id = ? AND title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, gallery.getId());
            preparedStatement.setString(2, imageTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            Image image = null;
            if (resultSet.next()) {
                image = new Image();
                image.setId(resultSet.getLong("id"));
                image.setTitle(resultSet.getString("title"));
                image.setDescription(resultSet.getString("description"));
            }
            return Optional.ofNullable(image);
        } catch (Throwable e) {
            System.out.println("Exception while execute findImageByTitle()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void remove(Gallery gallery) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "DELETE FROM gallery WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, gallery.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute remove()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void removeImageFromGallery(Gallery gallery, Image image) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "DELETE FROM image WHERE id = ? AND gallery_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, image.getId());
            preparedStatement.setLong(2, gallery.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute removeImageFromGallery()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Gallery> getAllGalleries() {
        List<Gallery> galleries = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM gallery";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gallery gallery = new Gallery();
                gallery.setId(resultSet.getLong("id"));
                gallery.setTitle(resultSet.getString("title"));
                gallery.setDescription(resultSet.getString("description"));
                galleries.add(gallery);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting list getAllGalleries()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return galleries;
    }

    @Override
    public List<Image> getAllImagesInAGallery(Gallery gallery) {
        List<Image> images = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM image WHERE gallery_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, gallery.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Image image = new Image();
                image.setId(resultSet.getLong("id"));
                image.setTitle(resultSet.getString("title"));
                image.setDescription(resultSet.getString("description"));
                images.add(image);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting list getAllGalleries()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return images;
    }

}
