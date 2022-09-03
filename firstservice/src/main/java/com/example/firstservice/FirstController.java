package com.example.firstservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/first-service")
public class FirstController {

    private final Environment env;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the first service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info("header: {}", header);
        return "Hello world in first service.";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server Port: {}", request.getServerPort());
        return String.format("Hi there. This is a message from First Service. Server Port: %s",
                env.getProperty("local.server.port"));
    }

}
