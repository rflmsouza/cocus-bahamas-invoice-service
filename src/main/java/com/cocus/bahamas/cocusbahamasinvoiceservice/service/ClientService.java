package com.cocus.bahamas.cocusbahamasinvoiceservice.service;

import com.cocus.bahamas.cocusbahamasinvoiceservice.dto.ClientDTO;
import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientsByInvoiceID(Long invoiceId);
    ClientDTO createNewUser(ClientDTO clientDTO);
}
