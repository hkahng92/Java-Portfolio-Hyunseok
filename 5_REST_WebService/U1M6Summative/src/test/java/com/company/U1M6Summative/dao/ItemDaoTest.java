package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
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
public class ItemDaoTest {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    InvoiceItemDao invoiceItemDao;

    @Autowired
    ItemDao itemDao;

    @Before
    public void setup() throws Exception {

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        for(InvoiceItem it : invoiceItemList){
            invoiceItemDao.deleteInvoiceItem(it.getInvoiceItemId());
        }
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        for(Invoice invoice : invoiceList){
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        }
        List<Customer> customerList = customerDao.getAllCustomers();
        for(Customer c : customerList){
            customerDao.deleteCustomer(c.getCustomerId());
        }
        List<Item> itemList = itemDao.getAllItems();
        for(Item i : itemList){
            itemDao.deleteItem(i.getItemId());
        }
    }

    @Test
    public void addGetDeleteItem() {

        //Create an item
        Item item = new Item();
        item.setName("Deadpool Costume");
        item.setDescription("Renting out a Deadpool costume");
        item.setDailyRate(new BigDecimal("99.99"));

        //Add it to the database & get the ID from the database
        item = itemDao.addItem(item);

        //Create a new object based on the Item we created
        Item fromDatabase = itemDao.getItem(item.getItemId());

        //Assert that the two items are equal
        assertEquals(item, fromDatabase);

        //Delete the item from the database
        itemDao.deleteItem(item.getItemId());

        fromDatabase = itemDao.getItem(item.getItemId());

        //Assert that the item from the database is null
        assertNull(fromDatabase);
     }

    @Test
    public void getAllItems() {

        //Create an item
        Item itemOne = new Item();
        itemOne.setName("Deadpool Costume");
        itemOne.setDescription("Renting out a Deadpool costume");
        itemOne.setDailyRate(new BigDecimal("99.99"));

        Item itemTwo = new Item();
        itemTwo.setName("Rental Item");
        itemTwo.setDescription("This is an item the store is renting out");
        itemTwo.setDailyRate(new BigDecimal("879.99"));

        itemOne = itemDao.addItem(itemOne);
        itemTwo = itemDao.addItem(itemTwo);

        List<Item> itemList = itemDao.getAllItems();

        assertEquals(2, itemList.size());
    }

    @Test
    public void updateItem() {

        //Create an item
        Item item = new Item();
        item.setName("Deadpool Costume");
        item.setDescription("Renting out a Deadpool costume");
        item.setDailyRate(new BigDecimal("99.99"));

        item = itemDao.addItem(item);

        item.setName("Rental Item");
        item.setDescription("This is an item the store is renting out");
        item.setDailyRate(new BigDecimal("879.99"));

        itemDao.updateItem(item);

        Item fromDatabase = itemDao.getItem(item.getItemId());

        assertEquals(item, fromDatabase);
    }
}
