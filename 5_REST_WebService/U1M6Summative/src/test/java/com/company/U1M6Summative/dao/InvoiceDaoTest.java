package com.company.U1M6Summative.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.company.U1M6Summative.model.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;

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

    @Test
    public void addGetDeleteInvoice() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johs@gmail.com");
        customer.setPhone("9765432133");
        customer.setCompany("Cognizant");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2010, 1, 5));
        invoice.setPickupDate(LocalDate.of(2010, 2, 5));
        invoice.setReturnDate(LocalDate.of(2010, 1, 9));
        invoice.setLateFee(new BigDecimal("21.95"));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice1);

    }
    @Test(expected  = DataIntegrityViolationException.class)
    public void addWithRefIntegrityException() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(67);
        invoice.setOrderDate(LocalDate.of(2010, 1, 5));
        invoice.setPickupDate(LocalDate.of(2010, 2, 5));
        invoice.setReturnDate(LocalDate.of(2010, 1, 8));
        invoice.setLateFee(new BigDecimal("21.95"));
        invoice = invoiceDao.addInvoice(invoice);
    }
    @Test
    public void addInvoice() {

    }

    @Test
    public void getInvoice() {
    }

    @Test
    public void getAllInvoices() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johs@gmail.com");
        customer.setPhone("9765432133");
        customer.setCompany("Cognizant");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2010, 1, 5));
        invoice.setPickupDate(LocalDate.of(2010, 2, 5));
        invoice.setReturnDate(LocalDate.of(2010, 1, 9));
        invoice.setLateFee(new BigDecimal("21.95"));

        invoice = invoiceDao.addInvoice(invoice);

        customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Austen");
        customer.setEmail("janea@gmail.com");
        customer.setPhone("9765433422");
        customer.setCompany("CVS");
        customer = customerDao.addCustomer(customer);

        invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2011, 2, 5));
        invoice.setPickupDate(LocalDate.of(2011, 3, 6));
        invoice.setReturnDate(LocalDate.of(2011, 4, 7));
        invoice.setLateFee(new BigDecimal("32.95"));

        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> aList = invoiceDao.getAllInvoices();

        assertEquals(aList.size(), 2);
    }



    @Test
    public void updateInvoice() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johs@gmail.com");
        customer.setPhone("9765432133");
        customer.setCompany("Cognizant");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2010, 1, 5));
        invoice.setPickupDate(LocalDate.of(2010, 2, 5));
        invoice.setReturnDate(LocalDate.of(2010, 1, 9));
        invoice.setLateFee(new BigDecimal("21.95"));
        invoice = invoiceDao.addInvoice(invoice);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Austen");
        customer2.setEmail("janea@gmail.com");
        customer2.setPhone("9766572133");
        customer2.setCompany("CVS");
        customer2 = customerDao.addCustomer(customer2);

        invoice.setCustomerId(customer2.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2011, 2, 6));
        invoice.setPickupDate(LocalDate.of(2011, 3, 7));
        invoice.setReturnDate(LocalDate.of(2012, 4, 9));
        invoice.setLateFee(new BigDecimal("11.87"));

        invoiceDao.updateInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice1, invoice);
    }

    @Test
    public void deleteInvoice() {
    }

    @Test
    public void getInvoicesByCustomer() {
    }
}