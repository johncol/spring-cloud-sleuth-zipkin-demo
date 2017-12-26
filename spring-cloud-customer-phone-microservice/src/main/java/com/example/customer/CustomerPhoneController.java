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
@RequestMapping("/customer/phone")
public class CustomerPhoneController {

    public static final Map<Long, String> customerPhones;

    static {
        customerPhones = new HashMap<>();
        customerPhones.put(1L, "305-772-9982");
        customerPhones.put(2L, "305-889-2135");
        customerPhones.put(3L, "305-685-7821");
        customerPhones.put(4L, "305-112-3699");
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{customerId}")
    public String getCustomerPhone(@PathVariable Long customerId) {
        log.info("Customer[{}] phone requested", customerId);

        restTemplate.getForEntity("http://localhost:8003/thread/sleep/" + customerId, String.class).getBody();

        return customerPhones.get(customerId);
    }

}
