package com.company.HyunseokKahngU1Capstone.controller;

import com.company.HyunseokKahngU1Capstone.exception.InvalidItemTypeException;
import com.company.HyunseokKahngU1Capstone.exception.NotFoundException;
import com.company.HyunseokKahngU1Capstone.exception.QuantityValidationException;
import com.company.HyunseokKahngU1Capstone.service.GameStoreServiceLayer;
import com.company.HyunseokKahngU1Capstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    GameStoreServiceLayer gameStoreServiceLayer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoice) throws QuantityValidationException, InvalidItemTypeException {
        return gameStoreServiceLayer.saveInvoice(invoice);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable("id") int invoiceId){
        InvoiceViewModel invoiceViewModel = gameStoreServiceLayer.findInvoiceById(invoiceId);
        if(invoiceViewModel == null){
            throw new NotFoundException("Invoice could not be retrieved for id " + invoiceId);
        }
        return invoiceViewModel;
    }

    @GetMapping(value="/allDone")
    public String allDone(){
        return "You have successfully logged out.";
    }

}
