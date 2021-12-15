package com.cocus.bahamas.cocusbahamasinvoiceservice.dto;

import com.cocus.bahamas.cocusbahamasinvoiceservice.domain.Client;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ClientDTO {

    private Long id;
    private String name;
    private String email;
    private Long fiscalId;
    private Long invoiceId;

    public static ClientDTO toDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .fiscalId(client.getFiscalId())
                .invoiceId(client.getInvoiceId())
                .build();
    }
}
