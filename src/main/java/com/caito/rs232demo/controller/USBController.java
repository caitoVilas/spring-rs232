package com.caito.rs232demo.controller;

import com.caito.rs232demo.enums.PortName;
import com.caito.rs232demo.services.USBService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usb")
@RequiredArgsConstructor
@Tag(name = "USB")
public class USBController {
    private final USBService usbService;

    @PostMapping("/initialize")
    @Operation(summary = "USB port initialization")
    public void initilize(@RequestParam PortName portName) {

        usbService.connect(portName.name());
    }

    @PostMapping("/send")
    @Operation(summary = "sending message to USB")
    public String send(@RequestParam String data) {
        usbService.sendData(data);
        return "OK";
    }

    @PostMapping("/recive")
    @Operation(summary = "message reception from USB")
    public String recive() {
        return usbService.reciveData();
    }

    @PostMapping("/close")
    @Operation(summary = "USB port closure")
    public String close() {
        usbService.disconnect();
        return "port closed";
    }
}
