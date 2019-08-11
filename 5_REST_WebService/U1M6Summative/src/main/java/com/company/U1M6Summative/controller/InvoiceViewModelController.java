package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.service.ServiceLayer;
import com.company.U1M6Summative.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceViewModelController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

    @RequestMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoice(@PathVariable("customerId") int customerId){
        return serviceLayer.findInvoiceByCustomer(customerId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String removeInvoice(@PathVariable int id) {
        serviceLayer.removeInvoice(id);
        return "Invoice is deleted";
    }
}
