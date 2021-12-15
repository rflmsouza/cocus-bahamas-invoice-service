package com.cocus.bahamas.cocusbahamasinvoiceservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvoicesClientNotFoundException extends RuntimeException {

    public InvoicesClientNotFoundException(Long invoiceId) {
        super(String.format("NO CLIENTS FOUND FOR INVOICEID: %d", invoiceId));
    }
}

