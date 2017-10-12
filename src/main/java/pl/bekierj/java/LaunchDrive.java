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

    private void go() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.config/spring-config.xml");

        AppDAO appDAO = (AppDAO)ctx.getBean("appDao");

        Book book1 = new Book();
        book1.setTitle("About Java");
        book1.setPublishingDate(new Date());
        book1.setPages(100);
        book1.setAuthors(new HashSet<Author>());

        Author author1 = new Author();
        author1.setName("Jack Brown");
        author1.setArticles(new HashSet<Publication>());

        //association
        book1.getAuthors().add(author1);
        author1.getArticles().add(book1);

        appDAO.persistAuthor(author1);
        appDAO.persistPublication(book1);
    }
}