package com.example.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerAggregateController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{customerId}")
    public String getCustomerPhone(@PathVariable Long customerId) {
        log.info("Customer[{}] aggregate info requested", customerId);

        String phone = restTemplate.getForEntity("http://localhost:8001/customer/phone/" + customerId, String.class).getBody();
        String email = restTemplate.getForEntity("http://localhost:8002/customer/email/" + customerId, String.class).getBody();

        return String.format("Customer Data [Phone: %s, Email: %s]", phone, email);
    }

}
