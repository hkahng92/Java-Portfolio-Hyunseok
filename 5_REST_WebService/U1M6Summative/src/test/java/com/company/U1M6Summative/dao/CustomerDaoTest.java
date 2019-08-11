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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    protected  CustomerDao customerDao;

    @Autowired
    protected InvoiceDao invoiceDao;

    @Autowired
    protected InvoiceItemDao invoiceItemDao;

    @Autowired
    protected ItemDao itemDao;

    //************************************************
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

    //******************************************************

    @Test
    public void addCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Bill");
        customer.setLastName("King");
        customer.setEmail("www.email@gmail.com");
        customer.setCompany("Dunder Mifflen");
        customer.setPhone("800-555-1234");
        customer = customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer1, customer);

        customerDao.deleteCustomer(customer.getCustomerId());

        customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertNull(customer1);
    }


    @Test
    public void getAllCustomers() {

        Customer customer = new Customer();
        customer.setFirstName("Bill");
        customer.setLastName("King");
        customer.setEmail("www.email@gmail.com");
        customer.setCompany("Dunder Mifflen");
        customer.setPhone("800-555-1234");
        customerDao.addCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("www.getemail@gmail.com");
        customer1.setCompany("Cognizant");
        customer1.setPhone("888-454-3221");
        customerDao.addCustomer(customer1);

        List<Customer> customerList = customerDao.getAllCustomers();

        assertEquals(customerList.size(), 2);
    }

    @Test
    public void updateCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Bill");
        customer.setLastName("King");
        customer.setEmail("www.email@gmail.com");
        customer.setCompany("Dunder Mifflen");
        customer.setPhone("800-555-1234");
        customerDao.addCustomer(customer);

        customer.setFirstName("Jon");
        customer.setLastName("Doe");
        customer.setEmail("www.newemail@gmail.com");
        customer.setCompany("New Company");
        customer.setPhone("555-555-3333");

        customerDao.updateCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer1, customer);
    }

}