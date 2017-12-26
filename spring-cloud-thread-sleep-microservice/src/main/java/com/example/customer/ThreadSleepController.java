package com.example.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/thread/sleep")
public class ThreadSleepController {

    @Autowired
    private Tracer tracer;

    @GetMapping("/{customerId}")
    public String sleep(@PathVariable Long customerId) throws InterruptedException {

        Span span = tracer.createSpan("custom-span-for-thread-sleep");

        tracer.addTag("customerId", customerId.toString());

        span.logEvent("Sleep is about to start");
        Thread.sleep(new Random().nextInt(20) * 100);
        span.logEvent("Sleep just ended");

        tracer.close(span);

        return "OK";
    }

}
