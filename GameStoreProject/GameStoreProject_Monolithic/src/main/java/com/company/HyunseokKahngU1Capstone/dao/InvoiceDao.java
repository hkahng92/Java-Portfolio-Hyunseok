package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoices();

    void deleteInvoice(int id);
}
