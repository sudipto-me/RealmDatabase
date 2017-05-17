package app.realm.gnt.com.realmdatabase;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



/**
 * Created by PC-05 on 5/17/2017.
 */

public class Book extends RealmObject {
    @PrimaryKey

    private int id;
    private String title;
    private String author;
    private String description;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
