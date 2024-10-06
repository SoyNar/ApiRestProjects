package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test")
@RestController
public class UserController {


    public class TestController {
        @GetMapping
        public String test() {
            return "¡Acceso exitoso!";
        }
    }
}