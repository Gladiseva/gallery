package lv.javaguru.java2.database;

import lv.javaguru.java2.Gallery;
import lv.javaguru.java2.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GalleryInMemoryDatabaseTest {

    @Mock
    private Gallery gallery;

    @Spy
    @InjectMocks
    private GalleryInMemoryDatabase database;

    @Test
    public void addImageToGallery() {
        database.addImageToGallery(gallery, "image");

        verify(gallery, times(1)).addImageToGallery(any(Image.class));
    }

    @Test
    public void add() {
        database.add(gallery);

        boolean contains = database.getAllGalleries().contains(gallery);
        assertTrue(contains);
    }

    @Test
    public void remove() {
        database.add(gallery);
        database.remove(gallery);

        boolean contains = database.getAllGalleries().contains(gallery);
        assertFalse(contains);
    }

    @Test
    public void getAllGalleries() {
        database.add(gallery);

        List<Gallery> galleries = database.getAllGalleries();

        assertTrue(galleries instanceof ArrayList);
        assertThat(galleries, hasSize(1));
    }
}