package pl.bekierj.java.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bekierj.java.model.Author;
import pl.bekierj.java.model.Publication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by BekierJ on 2017-10-11.
 */
@Component
public class AppDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persistAuthor(Author author) {
        entityManager.persist(author);
    }

    @Transactional
    public void persistPublication(Publication publication) {
        entityManager.persist(publication);
    }

}
