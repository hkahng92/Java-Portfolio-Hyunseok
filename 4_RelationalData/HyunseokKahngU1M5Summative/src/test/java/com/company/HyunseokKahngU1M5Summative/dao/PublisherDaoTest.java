package com.company.HyunseokKahngU1M5Summative.dao;

import com.company.HyunseokKahngU1M5Summative.model.Author;
import com.company.HyunseokKahngU1M5Summative.model.Book;
import com.company.HyunseokKahngU1M5Summative.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        List<Book> bookList = bookDao.getAllBooks();
        for(Book b : bookList){
            bookDao.deleteBook(b.getId());
        }
        List<Author> authorList = authorDao.getAllAuthors();
        for(Author a : authorList){
            authorDao.deleteAuthor(a.getId());
        }
        List<Publisher> publisherList = publisherDao.getAllPublishers();
        for(Publisher p : publisherList){
            publisherDao.deletePublisher(p.getId());
        }
    }

    @Test
    public void addGetDeletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("K&K publish");
        publisher.setStreet("Franklin St");
        publisher.setCity("Haworth");
        publisher.setState("NJ");
        publisher.setPostalCode("07631");
        publisher.setPhone("201 111 3333");
        publisher.setEmail("kandkpublisher@gmail.com");

        publisher = publisherDao.addPublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());

        assertEquals(publisher,publisher1);

        publisherDao.deletePublisher(publisher.getId());

        publisher1 = publisherDao.getPublisher(publisher.getId());

        assertNull(publisher1);


    }

    @Test
    public void getAllPublishers() {
        Publisher publisher = new Publisher();
        publisher.setName("K&K publish");
        publisher.setStreet("Franklin St");
        publisher.setCity("Haworth");
        publisher.setState("NJ");
        publisher.setPostalCode("07631");
        publisher.setPhone("201 111 3333");
        publisher.setEmail("kandkpublisher@gmail.com");

        publisherDao.addPublisher(publisher);

        publisher.setName("Number One Publishers");
        publisher.setStreet("Haring St");
        publisher.setCity("Closter");
        publisher.setState("NY");
        publisher.setPostalCode("10006");
        publisher.setPhone("201 312 6666");
        publisher.setEmail("NumberOne@gmail.com");

        publisherDao.addPublisher(publisher);

        List<Publisher> publisherList = publisherDao.getAllPublishers();
        assertEquals(publisherList.size(),2);
    }

    @Test
    public void updatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("K&K publish");
        publisher.setStreet("Franklin St");
        publisher.setCity("Haworth");
        publisher.setState("NJ");
        publisher.setPostalCode("07631");
        publisher.setPhone("201 111 3333");
        publisher.setEmail("kandkpublisher@gmail.com");

        publisherDao.addPublisher(publisher);

        publisher.setName("Number One Publishers");
        publisher.setStreet("Haring St");
        publisher.setCity("Closter");
        publisher.setState("NY");
        publisher.setPostalCode("10006");
        publisher.setPhone("201 312 6666");
        publisher.setEmail("NumberOne@gmail.com");

        publisherDao.updatePublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());

        assertEquals(publisher1,publisher);
    }
}