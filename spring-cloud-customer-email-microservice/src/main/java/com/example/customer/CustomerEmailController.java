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
@RequestMapping("/customer/email")
public class CustomerEmailController {

    public static final Map<Long, String> customerPhones;

    static {
        customerPhones = new HashMap<>();
        customerPhones.put(1L, "john.doe@mail.com");
        customerPhones.put(2L, "bruce.wayne@mail.com");
        customerPhones.put(3L, "michael.jackson@mail.com");
        customerPhones.put(4L, "madison.ivy@mail.com");
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{customerId}")
    public String getCustomerPhone(@PathVariable Long customerId) {
        log.info("Customer[{}] email requested", customerId);

        restTemplate.getForEntity("http://localhost:8003/thread/sleep/" + customerId, String.class).getBody();

        return customerPhones.get(customerId);
    }

}
