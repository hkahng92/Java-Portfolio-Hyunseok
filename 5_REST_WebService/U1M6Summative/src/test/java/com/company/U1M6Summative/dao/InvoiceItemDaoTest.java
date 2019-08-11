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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
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

    @Test(expected = DataIntegrityViolationException.class)
    public void addWithRefIntegrityException() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(3);
        invoiceItem.setItemId(5);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitRate(new BigDecimal("10.99"));
        invoiceItem.setDiscount(new BigDecimal("15.0"));
        invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    @Test
    public void addGetDeleteInvoiceItem() {
        //add customer first
        Customer customer = new Customer();
        customer.setFirstName("Lebron");
        customer.setLastName("James");
        customer.setEmail("LBJ@gmail.com");
        customer.setCompany("Clutch Sports");
        customer.setPhone("201 111 4444");
        customer = customerDao.addCustomer(customer);
        //add item next
        Item item = new Item();
        item.setName("Motorcycle");
        item.setDescription("New BMW Bike");
        item.setDailyRate(new BigDecimal("88.99"));
        item = itemDao.addItem(item);
        //add invoice first
        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2010,1,13));
        invoice.setPickupDate(LocalDate.of(2010,2,13));
        invoice.setReturnDate(LocalDate.of(2011,1,1));
        invoice.setLateFee(new BigDecimal("9999.99"));
        invoice = invoiceDao.addInvoice(invoice);

        //add invoice item
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(50);
        invoiceItem.setUnitRate(new BigDecimal("10.99"));
        invoiceItem.setDiscount(new BigDecimal("15.10"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem1, invoiceItem);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertNull(invoiceItem1);
    }

    @Test
    public void getAllInvoiceItems() {
        //add customer first
        Customer customer = new Customer();
        customer.setFirstName("Lebron");
        customer.setLastName("James");
        customer.setEmail("LBJ@gmail.com");
        customer.setCompany("Clutch Sports");
        customer.setPhone("201 111 4444");
        customer = customerDao.addCustomer(customer);
        //add item next
        Item item = new Item();
        item.setName("Motorcycle");
        item.setDescription("New BMW Bike");
        item.setDailyRate(new BigDecimal("88.99"));
        item = itemDao.addItem(item);
        //add invoice first
        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2010,1,13));
        invoice.setPickupDate(LocalDate.of(2010,2,13));
        invoice.setReturnDate(LocalDate.of(2011,1,1));
        invoice.setLateFee(new BigDecimal("9999.99"));
        invoice = invoiceDao.addInvoice(invoice);

        //add invoice item
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(50);
        invoiceItem.setUnitRate(new BigDecimal("10.99"));
        invoiceItem.setDiscount(new BigDecimal("15.10"));
        invoiceItemDao.addInvoiceItem(invoiceItem);
        //add invoice item 2
        invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitRate(new BigDecimal("20.99"));
        invoiceItem.setDiscount(new BigDecimal("40.15"));
        invoiceItemDao.addInvoiceItem(invoiceItem);

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        assertEquals(invoiceItemList.size(), 2);
    }

    @Test
    public void updateInvoiceItem() {
        //add customer first
        Customer customer = new Customer();
        customer.setFirstName("Lebron");
        customer.setLastName("James");
        customer.setEmail("LBJ@gmail.com");
        customer.setCompany("Clutch Sports");
        customer.setPhone("201 111 4444");
        customer = customerDao.addCustomer(customer);
        //add item next
        Item item = new Item();
        item.setName("Motorcycle");
        item.setDescription("New BMW Bike");
        item.setDailyRate(new BigDecimal("88.99"));
        item = itemDao.addItem(item);
        //add invoice first
        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2010,1,13));
        invoice.setPickupDate(LocalDate.of(2010,2,13));
        invoice.setReturnDate(LocalDate.of(2011,1,1));
        invoice.setLateFee(new BigDecimal("9999.99"));
        invoice = invoiceDao.addInvoice(invoice);

        //add invoice item
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(50);
        invoiceItem.setUnitRate(new BigDecimal("10.99"));
        invoiceItem.setDiscount(new BigDecimal("15.10"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        //update
        invoiceItem.setQuantity(200);
        invoiceItem.setUnitRate(new BigDecimal("30.99"));
        invoiceItem.setDiscount(new BigDecimal("40.01"));
        invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem,invoiceItem1);
    }

}