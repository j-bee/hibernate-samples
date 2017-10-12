package pl.bekierj.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Book")
public class Book extends Publication {

    @Column
    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
