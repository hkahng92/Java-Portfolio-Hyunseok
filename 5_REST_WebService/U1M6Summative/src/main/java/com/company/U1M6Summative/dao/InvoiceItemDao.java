package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int id);
    List<InvoiceItem> getAllInvoiceItems();
    List<InvoiceItem> getInvoiceItemsByInvoice(int invoiceId);
    InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem);
    boolean deleteInvoiceItem(int id);
}
