package com.company.HyunseokKahngU1Capstone.service;

import com.company.HyunseokKahngU1Capstone.dao.*;
import com.company.HyunseokKahngU1Capstone.exception.InvalidItemTypeException;
import com.company.HyunseokKahngU1Capstone.exception.QuantityValidationException;
import com.company.HyunseokKahngU1Capstone.model.*;
import com.company.HyunseokKahngU1Capstone.viewmodel.ConsoleViewModel;
import com.company.HyunseokKahngU1Capstone.viewmodel.GameViewModel;
import com.company.HyunseokKahngU1Capstone.viewmodel.InvoiceViewModel;
import com.company.HyunseokKahngU1Capstone.viewmodel.TShirtViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GameStoreServiceLayerTest {

    ConsoleDao consoleDao;
    GameDao gameDao;
    TShirtDao tShirtDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    GameStoreServiceLayer gameStoreServiceLayer;

    @Before
    public void setUp() throws Exception {
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpTShirtDaoMock();
        setUpInvoiceDaoMock();
        setUpSalesTaxRateDaoMock();
        setUpProcessingFeeDaoMock();
        gameStoreServiceLayer = new GameStoreServiceLayer(consoleDao,gameDao,tShirtDao,invoiceDao,processingFeeDao,salesTaxRateDao);
    }

    private void setUpConsoleDaoMock(){
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();

        console.setConsoleId(1);
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("16GB");
        console.setProcessor("8 core AMD");
        console.setPrice(new BigDecimal("299.5").setScale(2));
        console.setQuantity(100);

        Console console1 = new Console();

        console1.setModel("PlayStation 4");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("16GB");
        console1.setProcessor("8 core AMD");
        console1.setPrice(new BigDecimal("299.5").setScale(2));
        console1.setQuantity(100);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        Console console2 = new Console();

        console2.setConsoleId(1);
        console2.setModel("Xbox One");
        console2.setManufacturer("MicroSoft");
        console2.setMemoryAmount("16GB");
        console2.setProcessor("8 core AMD");
        console2.setPrice(new BigDecimal("399.5").setScale(2));
        console2.setQuantity(10);

        doReturn(console).when(consoleDao).addConsole(console1);
        doReturn(console).when(consoleDao).getConsole(1);
        doReturn(consoleList).when(consoleDao).getConsoleByManufacturer("Sony");
        doReturn(console2).when(consoleDao).updateConsole(console);
    }
    private void setUpGameDaoMock(){
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();

        game.setGameId(2);
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("official NBA video game");
        game.setPrice(new BigDecimal("59.99").setScale(2));
        game.setStudio("2K Studios");
        game.setQuantity(50);

        Game game1 = new Game();

        game1.setTitle("NBA 2K19");
        game1.setErsbRating("E");
        game1.setDescription("official NBA video game");
        game1.setPrice(new BigDecimal("59.99").setScale(2));
        game1.setStudio("2K Studios");
        game1.setQuantity(50);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        Game game2 = new Game();
        game2.setGameId(2);
        game2.setTitle("NBA Streets");
        game2.setErsbRating("M");
        game2.setDescription("official NBA Streets video game");
        game2.setPrice(new BigDecimal("49.99").setScale(2));
        game2.setStudio("NBA streets studios");
        game2.setQuantity(100);

        doReturn(game).when(gameDao).addGame(game1);
        doReturn(game).when(gameDao).getGame(2);
        doReturn(gameList).when(gameDao).getGameByStudio("2K Studios");
        doReturn(gameList).when(gameDao).getGameByErsb("E");
        doReturn(gameList).when(gameDao).getGameByTitle("NBA 2K19");
        doReturn(game2).when(gameDao).updateGame(game);

    }
    private void setUpTShirtDaoMock(){
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirt tShirt = new TShirt();

        tShirt.settShirtId(3);
        tShirt.setSize("M");
        tShirt.setColor("Red");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("99.99").setScale(2));
        tShirt.setQuantity(20);

        TShirt tShirt1 = new TShirt();

        tShirt1.setSize("M");
        tShirt1.setColor("Red");
        tShirt1.setDescription("Supreme T Shirt");
        tShirt1.setPrice(new BigDecimal("99.99").setScale(2));
        tShirt1.setQuantity(20);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt);

        TShirt tShirt2 = new TShirt();
        tShirt2.settShirtId(3);
        tShirt2.setSize("L");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Balenciaga T Shirt");
        tShirt2.setPrice(new BigDecimal("199.99").setScale(2));
        tShirt2.setQuantity(10);

        doReturn(tShirt).when(tShirtDao).addTShirt(tShirt1);
        doReturn(tShirt).when(tShirtDao).getTShirt(3);
        doReturn(tShirtList).when(tShirtDao).getTShirtByColor("Red");
        doReturn(tShirtList).when(tShirtDao).getTShirtBySize("M");
        doReturn(tShirt2).when(tShirtDao).updateTShirt(tShirt);
    }
    private void setUpInvoiceDaoMock(){
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();

        invoice.setInvoiceId(4);
        invoice.setName("Game Invoice");
        invoice.setStreet("Broad Ave");
        invoice.setCity("Palisades Park");
        invoice.setState("NJ");
        invoice.setZipcode("07033");
        invoice.setItemType("Games");
        invoice.setItemId(2);

        invoice.setUnitPrice(new BigDecimal("59.99").setScale(2));
        invoice.setQuantity(12);
        invoice.setSubtotal(new BigDecimal("719.88").setScale(2));
        invoice.setTax(new BigDecimal("35.99").setScale(2));
        invoice.setProcessingFee(new BigDecimal("16.98").setScale(2));
        invoice.setTotal(new BigDecimal("772.85").setScale(2));

        Invoice invoice1 = new Invoice();

        invoice1.setName("Game Invoice");
        invoice1.setStreet("Broad Ave");
        invoice1.setCity("Palisades Park");
        invoice1.setState("NJ");
        invoice1.setZipcode("07033");
        invoice1.setItemType("Games");
        invoice1.setItemId(2);

        invoice1.setUnitPrice(new BigDecimal("59.99").setScale(2));
        invoice1.setQuantity(12);
        invoice1.setSubtotal(new BigDecimal("719.88").setScale(2));
        invoice1.setTax(new BigDecimal("35.99").setScale(2));
        invoice1.setProcessingFee(new BigDecimal("16.98").setScale(2));
        invoice1.setTotal(new BigDecimal("772.85").setScale(2));

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(4);
    }

    private void setUpProcessingFeeDaoMock() {
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Games");
        processingFee.setFee(new BigDecimal("1.49").setScale(2));

        ProcessingFee processingFee1 = new ProcessingFee();
        processingFee1.setProductType("Consoles");
        processingFee1.setFee(new BigDecimal("14.99").setScale(2));

        ProcessingFee processingFee2 = new ProcessingFee();
        processingFee2.setProductType("T-Shirts");
        processingFee2.setFee(new BigDecimal("1.98").setScale(2));

        doReturn(processingFee).when(processingFeeDao).getProcessingFee("Games");
        doReturn(processingFee).when(processingFeeDao).getProcessingFee("Consoles");
        doReturn(processingFee).when(processingFeeDao).getProcessingFee("T-Shirts");
    }

    private void setUpSalesTaxRateDaoMock() {
        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(new BigDecimal(".05").setScale(2));

        SalesTaxRate salesTaxRate1 = new SalesTaxRate();
        salesTaxRate1.setState("CA");
        salesTaxRate1.setRate(new BigDecimal(".06").setScale(2));

        doReturn(salesTaxRate).when(salesTaxRateDao).getSalesTaxRate("NJ");
        doReturn(salesTaxRate1).when(salesTaxRateDao).getSalesTaxRate("CA");
    }


    @Test
    public void saveFindConsole() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("16GB");
        console.setProcessor("8 core AMD");
        console.setPrice(new BigDecimal("299.5").setScale(2));
        console.setQuantity(100);

        console = gameStoreServiceLayer.saveConsole(console);

        ConsoleViewModel fromService = gameStoreServiceLayer.findConsoleById(console.getConsoleId());
        assertEquals(console,fromService);
    }

    @Test
    public void updateConsole() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setConsoleId(1);
        console.setModel("Xbox One");
        console.setManufacturer("MicroSoft");
        console.setMemoryAmount("16GB");
        console.setProcessor("8 core AMD");
        console.setPrice(new BigDecimal("399.5").setScale(2));
        console.setQuantity(10);

        ConsoleViewModel fromService = gameStoreServiceLayer.updateConsole(console);
        assertEquals(console,fromService);
    }

    @Test
    public void findConsolesByManufacturer() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("16GB");
        console.setProcessor("8 core AMD");
        console.setPrice(new BigDecimal("299.5").setScale(2));
        console.setQuantity(100);

        console = gameStoreServiceLayer.saveConsole(console);

        List<ConsoleViewModel> consoles = gameStoreServiceLayer.findConsolesByManufacturer("Sony");
        assertEquals(consoles.size(),1);
        assertEquals(console,consoles.get(0));
    }

    @Test
    public void saveFindGame() {
        GameViewModel game = new GameViewModel();
        game.setGameId(2);
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("official NBA video game");
        game.setPrice(new BigDecimal("59.99").setScale(2));
        game.setStudio("2K Studios");
        game.setQuantity(50);

        game = gameStoreServiceLayer.saveGame(game);
        GameViewModel fromService = gameStoreServiceLayer.findGameById(game.getGameId());
        assertEquals(game,fromService);
    }

    @Test
    public void updateGame() {
        GameViewModel game = new GameViewModel();
        game.setGameId(2);
        game.setTitle("NBA Streets");
        game.setErsbRating("M");
        game.setDescription("official NBA Streets video game");
        game.setPrice(new BigDecimal("49.99").setScale(2));
        game.setStudio("NBA streets studios");
        game.setQuantity(100);

        GameViewModel fromService = gameStoreServiceLayer.updateGame(game);
        assertEquals(game,fromService);
    }

    @Test
    public void findGamesByStudio() {
        GameViewModel game = new GameViewModel();
        game.setGameId(2);
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("official NBA video game");
        game.setPrice(new BigDecimal("59.99").setScale(2));
        game.setStudio("2K Studios");
        game.setQuantity(50);

        game = gameStoreServiceLayer.saveGame(game);

        List<GameViewModel> games = gameStoreServiceLayer.findGamesByStudio("2K Studios");
        assertEquals(games.size(),1);
        assertEquals(game,games.get(0));
    }

    @Test
    public void findGamesByErsb() {
        GameViewModel game = new GameViewModel();
        game.setGameId(2);
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("official NBA video game");
        game.setPrice(new BigDecimal("59.99").setScale(2));
        game.setStudio("2K Studios");
        game.setQuantity(50);

        game = gameStoreServiceLayer.saveGame(game);

        List<GameViewModel> games = gameStoreServiceLayer.findGamesByErsb("E");
        assertEquals(games.size(),1);
        assertEquals(game,games.get(0));
    }

    @Test
    public void findGamesByTitle() {
        GameViewModel game = new GameViewModel();
        game.setGameId(2);
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("official NBA video game");
        game.setPrice(new BigDecimal("59.99").setScale(2));
        game.setStudio("2K Studios");
        game.setQuantity(50);

        game = gameStoreServiceLayer.saveGame(game);

        List<GameViewModel> games = gameStoreServiceLayer.findGamesByTitle("NBA 2K19");
        assertEquals(games.size(),1);
        assertEquals(game,games.get(0));
    }

    @Test
    public void saveFindTShirt() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.settShirtId(3);
        tShirt.setSize("M");
        tShirt.setColor("Red");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("99.99").setScale(2));
        tShirt.setQuantity(20);

        tShirt = gameStoreServiceLayer.saveTShirt(tShirt);
        TShirtViewModel fromService = gameStoreServiceLayer.findTShirtById(tShirt.gettShirtId());
        assertEquals(tShirt,fromService);
    }

    @Test
    public void updateTShirt() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.settShirtId(3);
        tShirt.setSize("L");
        tShirt.setColor("Blue");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("199.99").setScale(2));
        tShirt.setQuantity(10);

        TShirtViewModel fromService = gameStoreServiceLayer.updateTShirt(tShirt);

        assertEquals(tShirt,fromService);
    }

    @Test
    public void findTShirtsByColor() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.settShirtId(3);
        tShirt.setSize("M");
        tShirt.setColor("Red");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("99.99").setScale(2));
        tShirt.setQuantity(20);

        tShirt = gameStoreServiceLayer.saveTShirt(tShirt);

        List<TShirtViewModel> tShirts = gameStoreServiceLayer.findTShirtsByColor("Red");
        assertEquals(tShirts.size(),1);
        assertEquals(tShirt,tShirts.get(0));
    }

    @Test
    public void findTShirtsBySize() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.settShirtId(3);
        tShirt.setSize("M");
        tShirt.setColor("Red");
        tShirt.setDescription("Supreme T Shirt");
        tShirt.setPrice(new BigDecimal("99.99").setScale(2));
        tShirt.setQuantity(20);

        tShirt = gameStoreServiceLayer.saveTShirt(tShirt);

        List<TShirtViewModel> tShirts = gameStoreServiceLayer.findTShirtsBySize("M");
        assertEquals(tShirts.size(),1);
        assertEquals(tShirt,tShirts.get(0));
    }

    @Test
    public void saveFindInvoice() throws QuantityValidationException, InvalidItemTypeException {
        GameViewModel game = new GameViewModel();
        game.setGameId(2);
        game.setTitle("NBA 2K19");
        game.setErsbRating("E");
        game.setDescription("official NBA video game");
        game.setPrice(new BigDecimal("59.99").setScale(2));
        game.setStudio("2K Studios");
        game.setQuantity(50);

        gameStoreServiceLayer.saveGame(game);

        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("Game Invoice");
        invoice.setStreet("Broad Ave");
        invoice.setCity("Palisades Park");
        invoice.setState("NJ");
        invoice.setZipcode("07033");
        invoice.setItemType("Games");
        invoice.setItemId(2);

        invoice.setUnitPrice(new BigDecimal("59.99").setScale(2));
        invoice.setQuantity(12);
        invoice.setSubtotal(new BigDecimal("719.88").setScale(2));
        invoice.setTax(new BigDecimal("35.99").setScale(2));
        invoice.setProcessingFee(new BigDecimal("16.98").setScale(2));
        invoice.setTotal(new BigDecimal("772.85").setScale(2));

        invoice = gameStoreServiceLayer.saveInvoice(invoice);
        InvoiceViewModel fromService = gameStoreServiceLayer.findInvoiceById(invoice.getInvoiceId());
        assertEquals(invoice,fromService);
    }

}