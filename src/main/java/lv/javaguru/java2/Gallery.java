package lv.javaguru.java2;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gallery")
public class Gallery {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gallery_seq")
    @SequenceGenerator(
            name = "gallery_seq",
            sequenceName = "gallery_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery gallery = (Gallery) o;
        return Objects.equals(id, gallery.id) &&
                Objects.equals(title, gallery.title) &&
                Objects.equals(description, gallery.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description);
    }


}


