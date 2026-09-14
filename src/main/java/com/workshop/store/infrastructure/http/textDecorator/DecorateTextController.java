package com.workshop.store.infrastructure.http.textDecorator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecorateTextController {

    @PostMapping("/api/v1/decorate")
    public String decorateText(@RequestBody DecorateTextRequest request) {
        System.out.println(request);
        return request.toString();
    }

}
