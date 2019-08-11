package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.*;
import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.viewmodel.CustomerViewModel;
import com.company.U1M6Summative.viewmodel.InvoiceViewModel;
import com.company.U1M6Summative.viewmodel.ItemViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;
    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        setUpItemDaoMock();
        service = new ServiceLayer(customerDao,itemDao,invoiceDao,invoiceItemDao);
    }

    @Test
    public void saveFindFindAllCustomer() {

        CustomerViewModel cvm = new CustomerViewModel();
        cvm.setFirstName("Mark");
        cvm.setLastName("Pinho");
        cvm.setEmail("mark.pinho@outlook.com");
        cvm.setCompany("Cognizant");
        cvm.setPhone("222-222-2222");
        cvm = service.saveCustomer(cvm);

        CustomerViewModel fromService = service.findCustomer(cvm.getCustomerId());
        assertEquals(cvm, fromService);

        List<CustomerViewModel> fromServices = service.findAllCustomers();
        assertEquals(1, fromServices.size());
    }

    @Test
    public void updateCustomer() {

        CustomerViewModel cvm = new CustomerViewModel();
        cvm.setCustomerId(1);
        cvm.setFirstName("Bill");
        cvm.setLastName("King");
        cvm.setEmail("bill.king@gmail.com");
        cvm.setCompany("Cognizant");
        cvm.setPhone("333-333-3333");

        CustomerViewModel cvm2 =  service.updateCustomer(cvm);

        assertEquals(cvm, cvm2);

    }

    @Test
    public void removeCustomer() {
    }

    @Test
    public void saveInvoice() {

    }

    @Test
    public void saveFindFindAllInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setOrderDate(LocalDate.of(2019, 7, 1));
        ivm.setPickupDate(LocalDate.of(2019, 7, 8));
        ivm.setReturnDate(LocalDate.of(2019, 8, 1));
        ivm.setLateFee(new BigDecimal("4999.99"));


        ivm.setCustomerId(1);

        InvoiceItem invoiceItemCreate = new InvoiceItem();
        //invoiceItemCreate.setInvoiceId(1);
        invoiceItemCreate.setItemId(5);
        invoiceItemCreate.setQuantity(20);
        invoiceItemCreate.setDiscount(new BigDecimal("21.00"));
        invoiceItemCreate.setUnitRate(new BigDecimal("32.00"));


        List<InvoiceItem> iIList = new ArrayList<>();
        iIList.add(invoiceItemCreate);

        ivm.setInvoiceItemList(iIList);
        service.saveInvoice(ivm);
        List<InvoiceViewModel> fromServiceList = service.findInvoiceByCustomer(ivm.getCustomerId());
        assertEquals(ivm.getInvoiceId(), fromServiceList.get(0).getInvoiceId());

        assertEquals(1, fromServiceList.size());

    }

    @Test
    public void removeInvoice() {

    }

    @Test
    public void findInvoiceByCustomer() {
    }

    @Test
    public void saveFindFindAllItem() {
        ItemViewModel ivm = new ItemViewModel();

        ivm.setName("Ironman Suit: Mark XV");
        ivm.setDescription("Ironman 3 suit");
        ivm.setDailyRate(new BigDecimal("9999.99"));

        ivm = service.saveItem(ivm);
        ItemViewModel fromService = service.findItem(ivm.getItemId());
        //List<ItemViewModel> fromServices = service.findAllItems();
        assertEquals(ivm, fromService);

        List<ItemViewModel> fromServices = service.findAllItems();
        assertEquals(1, fromServices.size());
    }

    @Test
    public void updateItem() {
        ItemViewModel ivm = new ItemViewModel();

//        ivm.setName("Ironman Suit: Mark XV");
//        ivm.setDescription("Ironman 3 suit");
//        ivm.setDailyRate(new BigDecimal("9999.99"));
//
//        ivm = service.saveItem(ivm);
        ivm.setItemId(5);
        ivm.setName("Halloween costume");
        ivm.setDescription("For last day of Oct.");
        ivm.setDailyRate(new BigDecimal("15.99"));

        ItemViewModel ivm1 = service.updateItem(ivm);

        assertEquals(ivm,ivm1);
    }

    @Test
    public void removeItem() {
    }

    private void setUpCustomerDaoMock(){

        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Mark");
        customer.setLastName("Pinho");
        customer.setEmail("mark.pinho@outlook.com");
        customer.setCompany("Cognizant");
        customer.setPhone("222-222-2222");

        Customer customer2 = new Customer();
        customer2.setFirstName("Mark");
        customer2.setLastName("Pinho");
        customer2.setEmail("mark.pinho@outlook.com");
        customer2.setCompany("Cognizant");
        customer2.setPhone("222-222-2222");

        Customer update = new Customer();
        update.setCustomerId(1);
        update.setFirstName("Bill");
        update.setLastName("King");
        update.setEmail("bill.king@gmail.com");
        update.setCompany("Cognizant");
        update.setPhone("333-333-3333");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerDao).addCustomer(customer2);
        doReturn(customer).when(customerDao).getCustomer(1);
        doReturn(customerList).when(customerDao).getAllCustomers();
        doReturn(update).when(customerDao).updateCustomer(customer);

    }
    private void setUpInvoiceDaoMock(){

        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setOrderDate(LocalDate.of(2019, 7, 1));
        invoice.setPickupDate(LocalDate.of(2019, 7, 8));
        invoice.setReturnDate(LocalDate.of(2019, 8, 1));
        invoice.setLateFee(new BigDecimal("4999.99"));

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(1);
        invoice2.setCustomerId(1);
        invoice2.setOrderDate(LocalDate.of(2019, 7, 1));
        invoice2.setPickupDate(LocalDate.of(2019, 7, 8));
        invoice2.setReturnDate(LocalDate.of(2019, 8, 1));
        invoice2.setLateFee(new BigDecimal("4999.99"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
        doReturn(invoiceList).when(invoiceDao).getInvoicesByCustomer(1);

    }
    private void setUpInvoiceItemDaoMock(){
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);

        InvoiceItem invoiceItemReturn = new InvoiceItem();
        invoiceItemReturn.setInvoiceItemId(1);
        invoiceItemReturn.setInvoiceId(1);
        invoiceItemReturn.setItemId(5);
        invoiceItemReturn.setQuantity(20);
        invoiceItemReturn.setDiscount(new BigDecimal("21.00"));
        invoiceItemReturn.setUnitRate(new BigDecimal("32.00"));

        InvoiceItem invoiceItemCreate = new InvoiceItem();
        invoiceItemCreate.setInvoiceId(1);
        invoiceItemCreate.setItemId(5);
        invoiceItemCreate.setQuantity(20);
        invoiceItemCreate.setDiscount(new BigDecimal("21.00"));
        invoiceItemCreate.setUnitRate(new BigDecimal("32.00"));

        List<InvoiceItem> lList = new ArrayList<>();
        lList.add(invoiceItemReturn);

        doReturn(invoiceItemReturn).when(invoiceItemDao).addInvoiceItem(invoiceItemCreate);
        doReturn(invoiceItemReturn).when(invoiceItemDao).getInvoiceItem(1);
        doReturn(lList).when(invoiceItemDao).getAllInvoiceItems();

    }
    private void setUpItemDaoMock(){
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item();
        item.setItemId(5);
        item.setName("Ironman Suit: Mark XV");
        item.setDescription("Ironman 3 suit");
        item.setDailyRate(new BigDecimal("9999.99"));

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Item item2 = new Item();
        item2.setName("Ironman Suit: Mark XV");
        item2.setDescription("Ironman 3 suit");
        item2.setDailyRate(new BigDecimal("9999.99"));

        Item item3 = new Item();
        item3.setItemId(5);
        item3.setName("Halloween costume");
        item3.setDescription("For last day of Oct.");
        item3.setDailyRate(new BigDecimal("15.99"));

        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(5);

        doReturn(item3).when(itemDao).updateItem(item);
        doReturn(itemList).when(itemDao).getAllItems();


    }
}