package com.company.HyunseokKahngU1M5Summative.dao;

import com.company.HyunseokKahngU1M5Summative.model.Author;
import com.company.HyunseokKahngU1M5Summative.model.Book;
import com.company.HyunseokKahngU1M5Summative.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

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
    public void addGetDeleteBook() {
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

        Publisher publisher = new Publisher();
        publisher.setName("K&K publish");
        publisher.setStreet("Franklin St");
        publisher.setCity("Haworth");
        publisher.setState("NJ");
        publisher.setPostalCode("07631");
        publisher.setPhone("201 111 3333");
        publisher.setEmail("kandkpublisher@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("0-385-48451-8");
        book.setPublishDate(LocalDate.of(2010,1,13));
        book.setAuthorId(author.getId());
        book.setTitle("Tuesday with Morrie");
        book.setPublisherId(publisher.getId());

        book.setPrice(new BigDecimal("13.99"));

        book = bookDao.addBook(book);

        Book book1 = bookDao.getBook(book.getId());

        assertEquals(book1,book);

        bookDao.deleteBook(book.getId());

        book1 = bookDao.getBook(book.getId());

        assertNull(book1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addWithRefIntegrityException() {
        Book book = new Book();
        book.setIsbn("0-385-48451-8");
        book.setPublishDate(LocalDate.of(2010,1,13));
        book.setAuthorId(1);
        book.setTitle("Tuesday with Morrie");
        book.setPublisherId(2);
        book.setPrice(new BigDecimal("13.99"));
        book = bookDao.addBook(book);
    }

    @Test
    public void getAllBooks() {
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

        Publisher publisher = new Publisher();
        publisher.setName("K&K publish");
        publisher.setStreet("Franklin St");
        publisher.setCity("Haworth");
        publisher.setState("NJ");
        publisher.setPostalCode("07631");
        publisher.setPhone("201 111 3333");
        publisher.setEmail("kandkpublisher@gmail.com");
        publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("0-385-48451-8");
        book.setPublishDate(LocalDate.of(2010,1,13));
        book.setAuthorId(author.getId());
        book.setTitle("Tuesday with Morrie");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("13.99"));
        bookDao.addBook(book);

        book = new Book();
        book.setIsbn("0-385-55555-8");
        book.setPublishDate(LocalDate.of(2009,3,23));
        book.setAuthorId(author.getId());
        book.setTitle("Seven Things in Heaven");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("18.99"));
        bookDao.addBook(book);

        List<Book> bookList = bookDao.getAllBooks();

        assertEquals(bookList.size(),2);
    }

    @Test
    public void updateBook() {
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

        Publisher publisher = new Publisher();
        publisher.setName("K&K publish");
        publisher.setStreet("Franklin St");
        publisher.setCity("Haworth");
        publisher.setState("NJ");
        publisher.setPostalCode("07631");
        publisher.setPhone("201 111 3333");
        publisher.setEmail("kandkpublisher@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("0-385-48451-8");
        book.setPublishDate(LocalDate.of(2010,1,13));
        book.setAuthorId(author.getId());
        book.setTitle("Tuesday with Morrie");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("13.99"));
        book = bookDao.addBook(book);

        book.setIsbn("0-385-55555-8");
        book.setPublishDate(LocalDate.of(2009,3,23));
        book.setTitle("Seven Things in Heaven");
        book.setPrice(new BigDecimal("18.99"));

        bookDao.updateBook(book);

        Book book1 = bookDao.getBook(book.getId());

        assertEquals(book1,book);

    }

    @Test
    public void findBooksByAuthor() {
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

        Publisher publisher = new Publisher();
        publisher.setName("K&K publish");
        publisher.setStreet("Franklin St");
        publisher.setCity("Haworth");
        publisher.setState("NJ");
        publisher.setPostalCode("07631");
        publisher.setPhone("201 111 3333");
        publisher.setEmail("kandkpublisher@gmail.com");
        publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("0-385-48451-8");
        book.setPublishDate(LocalDate.of(2010,1,13));
        book.setAuthorId(author.getId());
        book.setTitle("Tuesday with Morrie");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("13.99"));
        bookDao.addBook(book);

        book = new Book();
        book.setIsbn("0-385-55555-8");
        book.setPublishDate(LocalDate.of(2009,3,23));
        book.setAuthorId(author.getId());
        book.setTitle("Seven Things in Heaven");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("18.99"));
        bookDao.addBook(book);

        Author author1 = new Author();
        author1.setFirstName("Kevin");
        author1.setLastName("Durant");
        author1.setStreet("brook St");
        author1.setCity("Brooklyn");
        author1.setState("NY");
        author1.setPostalCode("10002");
        author1.setPhone("201 333 7777");
        author1.setEmail("KDinNets@gmail.com");
        author1 = authorDao.addAuthor(author1);

        book = new Book();
        book.setIsbn("0-111-22222-8");
        book.setPublishDate(LocalDate.of(2005,12,11));
        book.setAuthorId(author1.getId());
        book.setTitle("Art of Racing in the Rain");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("10.99"));
        bookDao.addBook(book);

        List<Book> bookList = bookDao.findBooksByAuthor(author.getId());
        assertEquals(bookList.size(),2);

        bookList = bookDao.findBooksByAuthor(author1.getId());
        assertEquals(bookList.size(),1);

    }
}