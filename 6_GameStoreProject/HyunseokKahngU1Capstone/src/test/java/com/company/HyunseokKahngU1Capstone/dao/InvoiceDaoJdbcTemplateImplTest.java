package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.*;
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
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TShirtDao tShirtDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;
    @Autowired
    SalesTaxRateDao salesTaxRateDao;

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
    public void addGetDeleteInvoice() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("450.30"));
        tShirt.setQuantity(10);

        tShirt = tShirtDao.addTShirt(tShirt);

        Invoice invoice = new Invoice();
        invoice.setName("First Invoice");
        invoice.setStreet("King St");
        invoice.setCity("Akron");
        invoice.setState("OH");
        invoice.setZipcode("11133");
        invoice.setItemType("T-Shirts");
        invoice.setItemId(1);
        invoice.setUnitPrice(tShirt.getPrice());
        invoice.setQuantity(5);
        invoice.setSubtotal(new BigDecimal("100.00"));
        invoice.setTax(salesTaxRateDao.getSalesTaxRate("OH").getRate());
        invoice.setProcessingFee(processingFeeDao.getProcessingFee("T-Shirts").getFee());
        invoice.setTotal(new BigDecimal("135.34"));

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1,invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertNull(invoice1);
    }

    @Test
    public void getAllInvoices() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Black");
        tShirt.setDescription("Balenciaga T Shirt");
        tShirt.setPrice(new BigDecimal("450.30"));
        tShirt.setQuantity(10);

        tShirt = tShirtDao.addTShirt(tShirt);

        Invoice invoice = new Invoice();
        invoice.setName("First Invoice");
        invoice.setStreet("King St");
        invoice.setCity("Akron");
        invoice.setState("OH");
        invoice.setZipcode("11133");
        invoice.setItemType("T-Shirts");
        invoice.setItemId(1);
        invoice.setUnitPrice(tShirt.getPrice());
        invoice.setQuantity(5);
        invoice.setSubtotal(new BigDecimal("100.00"));
        invoice.setTax(salesTaxRateDao.getSalesTaxRate("OH").getRate());
        invoice.setProcessingFee(processingFeeDao.getProcessingFee("T-Shirts").getFee());
        invoice.setTotal(new BigDecimal("135.34"));

        invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setName("Second Invoice");
        invoice.setStreet("Clipper St");
        invoice.setCity("Oakland");
        invoice.setState("CA");
        invoice.setZipcode("23231");
        invoice.setItemType("T-Shirts");
        invoice.setItemId(3);
        invoice.setUnitPrice(tShirt.getPrice());
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("300.00"));
        invoice.setTax(salesTaxRateDao.getSalesTaxRate("CA").getRate());
        invoice.setProcessingFee(processingFeeDao.getProcessingFee("T-Shirts").getFee());
        invoice.setTotal(new BigDecimal("300.23"));

        invoiceDao.addInvoice(invoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        assertEquals(invoiceList.size(),2);

    }

}