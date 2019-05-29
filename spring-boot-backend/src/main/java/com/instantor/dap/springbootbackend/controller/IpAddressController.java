package com.instantor.dap.springbootbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/ip")
public class IpAddressController {

    /**
     * @return the ip-address of the caller
     */
    @GetMapping
    public String getIp() {
        return "0.0.0.0";
    }

}
