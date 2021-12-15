package com.cocus.bahamas.cocusbahamasinvoiceservice.repository;

import com.cocus.bahamas.cocusbahamasinvoiceservice.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<List<Client>> findClientsByInvoiceId(Long invoiceId);
}
