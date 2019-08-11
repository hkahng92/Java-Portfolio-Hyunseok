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
public class AuthorDaoTest {

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
    public void addGetDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("Tyler");
        author.setLastName("Johnson");
        author.setStreet("Hollywood St");
        author.setCity("Beverely Hills");
        author.setState("CA");
        author.setPostalCode("10005");
        author.setPhone("661 245 7777");
        author.setEmail("Ballers@gmail.com");

        author = authorDao.addAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());

        assertEquals(author1, author);

        authorDao.deleteAuthor(author.getId());

        author1 = authorDao.getAuthor(author.getId());

        assertNull(author1);
    }
    @Test
    public void getAllAuthors() {
        Author author = new Author();
        author.setFirstName("Tyler");
        author.setLastName("Johnson");
        author.setStreet("Hollywood St");
        author.setCity("Beverely Hills");
        author.setState("CA");
        author.setPostalCode("10005");
        author.setPhone("661 245 7777");
        author.setEmail("Ballers@gmail.com");

        authorDao.addAuthor(author);

        author.setFirstName("Kevin");
        author.setLastName("Durant");
        author.setStreet("brook St");
        author.setCity("Brooklyn");
        author.setState("NY");
        author.setPostalCode("10002");
        author.setPhone("201 333 7777");
        author.setEmail("KDinNets@gmail.com");

        authorDao.addAuthor(author);

        List<Author> authorList = authorDao.getAllAuthors();

        assertEquals(authorList.size(),2);
    }

    @Test
    public void updateAuthor() {
        Author author = new Author();
        author.setFirstName("Tyler");
        author.setLastName("Johnson");
        author.setStreet("Hollywood St");
        author.setCity("Beverely Hills");
        author.setState("CA");
        author.setPostalCode("10005");
        author.setPhone("661 245 7777");
        author.setEmail("Ballers@gmail.com");

        authorDao.addAuthor(author);

        author.setFirstName("Kevin");
        author.setLastName("Durant");
        author.setStreet("brook St");
        author.setCity("Brooklyn");
        author.setState("NY");
        author.setPostalCode("10002");
        author.setPhone("201 333 7777");
        author.setEmail("KDinNets@gmail.com");

        authorDao.updateAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());

        assertEquals(author1,author);
    }
}