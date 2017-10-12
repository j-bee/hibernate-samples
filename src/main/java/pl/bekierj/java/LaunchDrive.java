package pl.bekierj.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.bekierj.java.dao.AppDAO;
import pl.bekierj.java.model.Author;
import pl.bekierj.java.model.Book;
import pl.bekierj.java.model.Publication;

import java.util.Date;
import java.util.HashSet;

public class LaunchDrive {
    public static void main( String[] args ) {
        /*https://www.thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
        * based on the 'Joined' option (the last one in the article)
        * */
        new LaunchDrive().go();
    }

    /*watch out for 'hibernate.hbm2ddl.auto' - changed to none!*/
    private void go() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.config/spring-config.xml");

        AppDAO appDAO = (AppDAO)ctx.getBean("appDao");

        Book book1 = new Book();
        Author author1 = new Author();
        Author author2 = new Author();

        book1.setTitle("About Java");
        book1.setPublishingDate(new Date());
        book1.setPages(100);
        book1.setAuthors(new HashSet<Author>());

        author1.setName("Jack Brown");
        author1.setArticles(new HashSet<Publication>());
        author2.setName("Mary Jane");
        author2.setArticles(new HashSet<Publication>());

        //association
        book1.getAuthors().add(author1);
        author1.getArticles().add(book1);

        //https://stackoverflow.com/questions/14111607/manytomanymappedby-foo
        //adding another book to author's publications does not
        //affect the association table - because "Publication"
        //is the owner of the relationship, not "Author"
        author2.getArticles().add(book1);

        //adding another author to book's "Authors" DOES
        //affect the association table - because "Publication"
        //owns the @ManyToMany relationship
        book1.getAuthors().add(author2);

        appDAO.persistAuthor(author1);
        appDAO.persistAuthor(author2);
        appDAO.persistPublication(book1);
    }
}