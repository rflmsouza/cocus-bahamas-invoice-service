package com.cocus.bahamas.cocusbahamasinvoiceservice.domain;

import com.cocus.bahamas.cocusbahamasinvoiceservice.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long fiscalId;
    private Long invoiceId;

    public static Client toClient(ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .fiscalId(clientDTO.getFiscalId())
                .invoiceId(clientDTO.getInvoiceId())
                .build();
    }
}
