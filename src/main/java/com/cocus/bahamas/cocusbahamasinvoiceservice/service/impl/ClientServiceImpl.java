package com.cocus.bahamas.cocusbahamasinvoiceservice.service.impl;

import com.cocus.bahamas.cocusbahamasinvoiceservice.domain.Client;
import com.cocus.bahamas.cocusbahamasinvoiceservice.dto.ClientDTO;
import com.cocus.bahamas.cocusbahamasinvoiceservice.repository.ClientRepository;
import com.cocus.bahamas.cocusbahamasinvoiceservice.restclient.BahamasRestClient;
import com.cocus.bahamas.cocusbahamasinvoiceservice.service.ClientService;
import com.cocus.bahamas.cocusbahamasinvoiceservice.service.exception.InvoicesClientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final BahamasRestClient bahamasRestClient;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             BahamasRestClient bahamasRestClient) {
        this.clientRepository = clientRepository;
        this.bahamasRestClient = bahamasRestClient;
    }


    @Override
    public List<ClientDTO> getClientsByInvoiceID(Long invoiceId) {
        return clientRepository.findClientsByInvoiceId(invoiceId)
                .map(list -> list.stream().map(ClientDTO::toDTO)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new InvoicesClientNotFoundException(invoiceId));
    }

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) {

        ClientDTO savedClient = ClientDTO.toDTO(clientRepository.save(Client.toClient(clientDTO)));
        this.callExternalService(savedClient);

        return savedClient;
    }

    private void callExternalService(ClientDTO clientDTO) {

        ResponseEntity<Client> response = bahamasRestClient.callBahamasAPI(clientDTO);
        logger.info(String.valueOf(response.getStatusCode()));
    }
}
