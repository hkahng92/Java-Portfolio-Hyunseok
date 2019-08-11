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
public class ConsoleDaoJdbcTemplateImplTest {

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
    public void addGetDeleteConsole() {
        Console console = new Console();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("8GB");
        console.setProcessor("x86-64 AMD");
        console.setPrice(new BigDecimal("399.99"));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console1,console);

        consoleDao.deleteConsole(console.getConsoleId());
        console1 = consoleDao.getConsole(console.getConsoleId());
        assertNull(console1);
    }
    @Test
    public void updateConsole() {
        Console console = new Console();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("8GB");
        console.setProcessor("x86-64 AMD");
        console.setPrice(new BigDecimal("399.99"));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        console.setModel("Xbox One");
        console.setManufacturer("MicroSoft");
        console.setMemoryAmount("16GB");
        console.setProcessor("8 Core AMD");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(20);

        consoleDao.updateConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console1,console);
    }

    @Test
    public void getAllConsoles() {
        Console console = new Console();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("8GB");
        console.setProcessor("x86-64 AMD");
        console.setPrice(new BigDecimal("399.99"));
        console.setQuantity(10);

        consoleDao.addConsole(console);

        console = new Console();
        console.setModel("Xbox One");
        console.setManufacturer("MicroSoft");
        console.setMemoryAmount("16GB");
        console.setProcessor("8 Core AMD");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(20);

        consoleDao.addConsole(console);

        List<Console> consoleList = consoleDao.getAllConsoles();
        assertEquals(consoleList.size(),2);
    }

    @Test
    public void getConsoleByManufacturer() {
        Console console = new Console();
        console.setModel("Wii");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("8GB");
        console.setProcessor("x86-64 AMD");
        console.setPrice(new BigDecimal("399.99"));
        console.setQuantity(10);

        consoleDao.addConsole(console);

        console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("16GB");
        console.setProcessor("8 Core AMD");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(20);

        consoleDao.addConsole(console);

        List<Console> consoleList = consoleDao.getConsoleByManufacturer("Nintendo");
        assertEquals(2,consoleList.size());
    }
}