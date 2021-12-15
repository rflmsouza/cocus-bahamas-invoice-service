package com.cocus.bahamas.cocusbahamasinvoiceservice;

import com.cocus.bahamas.cocusbahamasinvoiceservice.domain.Client;
import com.cocus.bahamas.cocusbahamasinvoiceservice.dto.ClientDTO;
import com.cocus.bahamas.cocusbahamasinvoiceservice.repository.ClientRepository;
import com.cocus.bahamas.cocusbahamasinvoiceservice.restclient.BahamasRestClient;
import com.cocus.bahamas.cocusbahamasinvoiceservice.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

public class ClientServiceTests {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    ClientRepository clientRepository;
    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    BahamasRestClient bahamasRestClient;
    @InjectMocks
    ClientServiceImpl clientService;
    ClientDTO clientDTO;
    Client client;
    List<Client> clientList;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        client = Client.builder()
                .id(1L)
                .name("testClient")
                .email("testClient@example.com")
                .fiscalId(9999L)
                .invoiceId(1234L)
                .build();

        clientDTO = ClientDTO.builder()
                .id(1L)
                .name("testClient")
                .email("testClient@example.com")
                .fiscalId(9999L)
                .invoiceId(1234L)
                .build();

        clientList = new ArrayList<>();
        clientList.add(client);
    }

    @Test
    void shouldCreateNewClient(){

        doReturn(client).when(clientRepository).save(client);
        doReturn(ResponseEntity.ok().build()).when(bahamasRestClient).callBahamasAPI(clientDTO);

        ClientDTO response = clientService.createNewClient(clientDTO);

        assertEquals(clientDTO.getId(), response.getId());
        assertEquals(clientDTO.getName(), response.getName());
        assertEquals(clientDTO.getEmail(), response.getEmail());
        assertEquals(clientDTO.getFiscalId(), response.getFiscalId());
        assertEquals(clientDTO.getInvoiceId(), response.getInvoiceId());
    }

    @Test
    void shouldReturnClientList() {

        doReturn(Optional.of(clientList)).when(clientRepository).findClientsByInvoiceId(clientDTO.getInvoiceId());
        List<ClientDTO> resultList = clientService.getClientsByInvoiceID(clientDTO.getInvoiceId());

        assertTrue(resultList.size() > 0);
        assertEquals(clientDTO.getName(), resultList.get(0).getName());
    }
}
