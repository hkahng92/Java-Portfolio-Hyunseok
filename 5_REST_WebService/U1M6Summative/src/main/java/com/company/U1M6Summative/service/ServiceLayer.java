package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.viewmodel.CustomerViewModel;
import com.company.U1M6Summative.viewmodel.InvoiceViewModel;
import com.company.U1M6Summative.viewmodel.ItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    private CustomerDao customerDao;
    private ItemDao itemDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, ItemDao itemDao, InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.itemDao = itemDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

//--Customer API----------------------------------------------//
    @Transactional
    public CustomerViewModel saveCustomer(CustomerViewModel viewModel) {
        Customer customer = new Customer();
        customer.setFirstName(viewModel.getFirstName());
        customer.setLastName(viewModel.getLastName());
        customer.setEmail(viewModel.getEmail());
        customer.setCompany(viewModel.getCompany());
        customer.setPhone(viewModel.getPhone());
        customer = customerDao.addCustomer(customer);
        viewModel.setCustomerId(customer.getCustomerId());
        return viewModel;
    }

    public CustomerViewModel findCustomer(int id) {
        Customer customer = customerDao.getCustomer(id);
        return buildCustomerViewModel(customer);
    }

    public List<CustomerViewModel> findAllCustomers() {
        List<Customer> customerList = customerDao.getAllCustomers();

        List<CustomerViewModel> cvmList = new ArrayList<>();

        for(Customer cust : customerList){
            CustomerViewModel cvm = buildCustomerViewModel(cust);
            cvmList.add(cvm);
        }

        return cvmList;
    }

    @Transactional
    public CustomerViewModel updateCustomer(CustomerViewModel viewModel) {

        Customer customer = new Customer();
        customer.setCustomerId(viewModel.getCustomerId());
        customer.setFirstName(viewModel.getFirstName());
        customer.setLastName(viewModel.getLastName());
        customer.setEmail(viewModel.getEmail());
        customer.setCompany(viewModel.getCompany());
        customer.setPhone(viewModel.getPhone());

        customerDao.updateCustomer(customer);

        customer = customerDao.getCustomer(viewModel.getCustomerId());


        return viewModel;
    }

    @Transactional
    public void removeCustomer(int id) {

        customerDao.deleteCustomer(id);

    }
//------------------------------------------------------------//

//--Item API--------------------------------------------------//
    @Transactional
    public ItemViewModel saveItem(ItemViewModel viewModel) {
        Item i = new Item();
        i.setName(viewModel.getName());
        i.setDescription(viewModel.getDescription());
        i.setDailyRate(viewModel.getDailyRate());
        i = itemDao.addItem(i);

        viewModel.setItemId(i.getItemId());

        return viewModel;
    }

    public ItemViewModel findItem(int id) {
        Item item = itemDao.getItem(id);
        return buildItemViewModel(item);
    }

    public List<ItemViewModel> findAllItems() {
        List<Item> itemList = itemDao.getAllItems();

        List<ItemViewModel> ivmList = new ArrayList<>();

        for(Item item : itemList){
            ItemViewModel ivm = buildItemViewModel(item);
            ivmList.add(ivm);
        }

        return ivmList;
    }

    @Transactional
    public ItemViewModel updateItem(ItemViewModel viewModel) {
          Item item = new Item();
          item.setItemId(viewModel.getItemId());
          item.setName(viewModel.getName());
          item.setDescription(viewModel.getDescription());
          item.setDailyRate(viewModel.getDailyRate());

          itemDao.updateItem(item);

          return buildItemViewModel(item);

    }

    @Transactional
    public void removeItem(int id) {
          itemDao.deleteItem(id);
    }
//------------------------------------------------------------//
//--Invoice API-----------------------------------------------//

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoice) {

        Invoice inv = new Invoice();
        inv.setLateFee(invoice.getLateFee());
        inv.setOrderDate(invoice.getOrderDate());
        inv.setPickupDate(invoice.getPickupDate());
        inv.setReturnDate(invoice.getReturnDate());
        inv.setCustomerId(invoice.getCustomerId());
        inv = invoiceDao.addInvoice(inv);
        invoice.setInvoiceId(inv.getInvoiceId());

        // Add Album Id to Tracks and Persist Tracks
        List<InvoiceItem> iItems = invoice.getInvoiceItemList();
        if(iItems != null && iItems.size() > 0) {
            for(InvoiceItem item : iItems) {
                item.setInvoiceId(invoice.getInvoiceId());
                item = invoiceItemDao.addInvoiceItem(item);
            }
        }

        iItems = invoiceItemDao.getInvoiceItemsByInvoice(invoice.getInvoiceId());
        invoice.setInvoiceItemList(iItems);
        return  invoice;

    }
    @Transactional
    public void removeInvoice(int id) {
        // Remove all associated tracks first
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoice(id);

        invoiceItemList.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        // Remove album
        invoiceDao.deleteInvoice(id);

    }

    public List<InvoiceViewModel> findInvoiceByCustomer(int customerId) {
        List<Invoice> invoiceList = invoiceDao.getInvoicesByCustomer(customerId);
        List<InvoiceViewModel> ivmList = new ArrayList<>();
        for(Invoice in : invoiceList){
            InvoiceViewModel ivm = buildInvoiceViewModel(in);
            ivmList.add(ivm);
        }
        return ivmList;
    }
//------------------------------------------------------------//

    @Transactional
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(invoice.getInvoiceId());
        ivm.setLateFee(invoice.getLateFee());
        ivm.setOrderDate(invoice.getOrderDate());
        ivm.setPickupDate(invoice.getPickupDate());
        ivm.setReturnDate(invoice.getReturnDate());
        List<InvoiceItem> iList = invoiceItemDao.getAllInvoiceItems();
        ivm.setCustomerId(invoice.getCustomerId());
        ivm.setInvoiceItemList(iList);
        return ivm;
    }

    @Transactional
    private CustomerViewModel buildCustomerViewModel(Customer customer) {
        CustomerViewModel cvm = new CustomerViewModel();

        cvm.setCustomerId(customer.getCustomerId());
        cvm.setFirstName(customer.getFirstName());
        cvm.setLastName(customer.getLastName());
        cvm.setEmail(customer.getEmail());
        cvm.setCompany(customer.getCompany());
        cvm.setPhone(customer.getPhone());

        return cvm;
    }
    @Transactional
    private ItemViewModel buildItemViewModel(Item item) {

        ItemViewModel ivm = new ItemViewModel();
        ivm.setItemId(item.getItemId());
        ivm.setName(item.getName());
        ivm.setDescription(item.getDescription());
        ivm.setDailyRate(item.getDailyRate());

        return ivm;
    }



}
