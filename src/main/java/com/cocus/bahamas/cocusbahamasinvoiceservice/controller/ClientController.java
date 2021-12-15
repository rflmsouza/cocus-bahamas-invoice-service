package com.cocus.bahamas.cocusbahamasinvoiceservice.controller;

import com.cocus.bahamas.cocusbahamasinvoiceservice.dto.ClientDTO;
import com.cocus.bahamas.cocusbahamasinvoiceservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/retrieve-bahamas-client/{invoiceId}")
    public CollectionModel<ClientDTO> getClient(@PathVariable("invoiceId") Long invoiceId) {
        return CollectionModel.of(clientService.getClientsByInvoiceID(invoiceId),
                linkTo(methodOn(ClientController.class).getClient(invoiceId)).withSelfRel());
    }

    @PostMapping("/store-bahamas-client/{invoiceId}")
    public EntityModel<ClientDTO> postClient(
            @PathVariable("invoiceId") Long invoiceId, @RequestParam Long fiscalId,
            @RequestParam String name, @RequestParam String email) {

        ClientDTO clientToCreate = ClientDTO.builder()
                .invoiceId(invoiceId)
                .fiscalId(fiscalId)
                .name(name)
                .email(email)
                .build();
        return EntityModel.of(clientService.createNewClient(clientToCreate),
                linkTo(methodOn(ClientController.class).getClient(invoiceId)).withRel("client"));
    }
}