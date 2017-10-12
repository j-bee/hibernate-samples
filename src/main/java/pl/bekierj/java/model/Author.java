package pl.bekierj.java.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    private Set<Publication> articles = new HashSet();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Publication> getArticles() {
        return articles;
    }

    public void setArticles(Set<Publication> articles) {
        this.articles = articles;
    }

}
