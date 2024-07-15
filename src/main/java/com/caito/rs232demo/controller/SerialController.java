package com.caito.rs232demo.controller;

import com.caito.rs232demo.enums.PortName;
import com.caito.rs232demo.services.SerialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serial")
@RequiredArgsConstructor
@Tag(name = "RS-232")
public class SerialController {
    private final SerialService serialService;

    @PostMapping("/initialize")
    @Operation(summary = "RS-232 port initialization")
    public String initilize(@RequestParam PortName portName) {

        return serialService.initialize(portName.name());
    }

    @PostMapping("/send")
    @Operation(summary = "sending message to RS-232")
    public String send(@RequestParam String data) {
        return serialService.sendData(data);
    }

    @PostMapping("/recive")
    @Operation(summary = "message reception from RS-232")
    public String recive() {
        return serialService.reciveData();
    }

    @PostMapping("/close")
    @Operation(summary = "RS232 port closure")
    public String close() {
        serialService.close();
        return "port closed";
    }
}
