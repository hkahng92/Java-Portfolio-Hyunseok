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
public class GameDaoJdbcTemplateImplTest {

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
    public void addGetDeleteGame() {
        Game game = new Game();
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("NBA simulation game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("2K Studios");
        game.setQuantity(100);

        Game game1 = gameDao.addGame(game);
        assertEquals(game1,game);

        gameDao.deleteGame(game.getGameId());
        game1 = gameDao.getGame(game.getGameId());
        assertNull(game1);
    }


    @Test
    public void updateGame() {
        Game game = new Game();
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("NBA simulation game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("2K Studios");
        game.setQuantity(100);

        gameDao.addGame(game);

        game.setTitle("Madden 19");
        game.setErsbRating("M");
        game.setDescription("NFL game");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("EA Sports");
        game.setQuantity(20);

        gameDao.updateGame(game);

        Game game1 = gameDao.getGame(game.getGameId());

        assertEquals(game1,game);
    }

    @Test
    public void getAllGames() {
        Game game = new Game();
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("NBA simulation game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("2K Studios");
        game.setQuantity(100);

        gameDao.addGame(game);

        game = new Game();
        game.setTitle("Madden 19");
        game.setErsbRating("M");
        game.setDescription("NFL game");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("EA Sports");
        game.setQuantity(20);

        gameDao.addGame(game);

        List<Game> gameList = gameDao.getAllGames();

        assertEquals(gameList.size(),2);
    }


    @Test
    public void getGameByStudio() {
        Game game = new Game();
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("NBA simulation game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("2K Studios");
        game.setQuantity(100);

        gameDao.addGame(game);

        game = new Game();
        game.setTitle("Madden 19");
        game.setErsbRating("M");
        game.setDescription("NFL game");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("2K Studios");
        game.setQuantity(20);

        gameDao.addGame(game);

        List<Game> gameList = gameDao.getGameByStudio("2K Studios");
        assertEquals(gameList.size(),2);
    }

    @Test
    public void getGameByEsrb() {
        Game game = new Game();
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("NBA simulation game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("2K Studios");
        game.setQuantity(100);

        gameDao.addGame(game);

        game = new Game();
        game.setTitle("Madden 19");
        game.setErsbRating("E");
        game.setDescription("NFL game");
        game.setPrice(new BigDecimal("49.99"));
        game.setStudio("2K Studios");
        game.setQuantity(20);

        gameDao.addGame(game);

        List<Game> gameList = gameDao.getGameByErsb("E");
        assertEquals(gameList.size(),2);

    }

    @Test
    public void getGameByTitle() {
        Game game = new Game();
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("NBA simulation game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("2K Studios");
        game.setQuantity(100);

        gameDao.addGame(game);

        game = new Game();
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("NBA simulation game. Lengend edition");
        game.setPrice(new BigDecimal("89.99"));
        game.setStudio("2K Studios");
        game.setQuantity(20);

        gameDao.addGame(game);

        List<Game> gameList = gameDao.getGameByTitle("NBA 2K19");
        assertEquals(gameList.size(),2);
    }
}