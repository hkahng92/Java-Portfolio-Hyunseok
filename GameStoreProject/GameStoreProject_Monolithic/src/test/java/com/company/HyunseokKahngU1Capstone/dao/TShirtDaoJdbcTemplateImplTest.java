package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.Console;
import com.company.HyunseokKahngU1Capstone.model.Game;
import com.company.HyunseokKahngU1Capstone.model.Invoice;
import com.company.HyunseokKahngU1Capstone.model.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtDaoJdbcTemplateImplTest {

    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TShirtDao tShirtDao;
    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        List<Console> consoles = consoleDao.getAllConsoles();
        for(Console c : consoles){
            consoleDao.deleteConsole(c.getConsoleId());
        }
        List<Game> games = gameDao.getAllGames();
        for(Game game : games){
            gameDao.deleteGame(game.getGameId());
        }
        List<TShirt> tShirts = tShirtDao.getAllTShirts();
        for(TShirt t : tShirts){
            tShirtDao.deleteTShirt(t.gettShirtId());
        }
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        for(Invoice invoice : invoices){
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        }
    }

    @Test
    public void addGetDeleteTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("450.30"));
        tShirt.setQuantity(10);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirt.gettShirtId());

        assertEquals(tShirt,tShirt1);

        tShirtDao.deleteTShirt(tShirt.gettShirtId());
        tShirt1 = tShirtDao.getTShirt(tShirt.gettShirtId());
        assertNull(tShirt1);
    }

    @Test
    public void getAllTShirts() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("450.30"));
        tShirt.setQuantity(10);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Red");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("230.10"));
        tShirt.setQuantity(30);

        tShirtDao.addTShirt(tShirt);

        List<TShirt> tList = tShirtDao.getAllTShirts();
        assertEquals(tList.size(),2);
    }

    @Test
    public void updateTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("450.30"));
        tShirt.setQuantity(10);

        tShirtDao.addTShirt(tShirt);

        tShirt.setSize("L");
        tShirt.setColor("Red");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("230.10"));
        tShirt.setQuantity(30);

        tShirtDao.updateTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirt.gettShirtId());

        assertEquals(tShirt,tShirt1);
    }

    @Test
    public void getTShirtByColor() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("450.30"));
        tShirt.setQuantity(10);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Black");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("230.10"));
        tShirt.setQuantity(30);

        tShirtDao.addTShirt(tShirt);

        List<TShirt> tList = tShirtDao.getTShirtByColor("Black");
        assertEquals(tList.size(),2);
    }

    @Test
    public void getTShirtBySize() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("450.30"));
        tShirt.setQuantity(10);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("230.10"));
        tShirt.setQuantity(30);

        tShirtDao.addTShirt(tShirt);

        List<TShirt> tList = tShirtDao.getTShirtBySize("M");
        assertEquals(tList.size(),2);
    }
}