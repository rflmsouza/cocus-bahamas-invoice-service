package com.cocus.bahamas.cocusbahamasinvoiceservice.restclient;

import com.cocus.bahamas.cocusbahamasinvoiceservice.domain.Client;
import com.cocus.bahamas.cocusbahamasinvoiceservice.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class BahamasRestClient {

    private final Logger logger = LoggerFactory.getLogger(BahamasRestClient.class);

    public ResponseEntity<Client> callBahamasAPI(ClientDTO payload) {

        String urlToCall = String.format("https://bahamas.gov/register?invoice=%d&fiscal_id=%d&name=%s&email=%s",
                payload.getInvoiceId(), payload.getFiscalId(), payload.getName(), payload.getEmail());

        try {
            logger.info("Mock API Calling");
            logger.info(urlToCall);

            Thread.sleep(ThreadLocalRandom.current().nextInt(0,200));
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        logger.info("Responding StatusCode 200");
        return ResponseEntity.ok().build();
    }
}
